package hello;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanFilter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import hello.csv.CustomerInfo;
import hello.data.jpa.domain.Customer;
import hello.data.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import hello.storage.StorageService;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    StorageService storageService;

    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    public String upload() {
        return "uploadForm";
    }

    @GetMapping("/list")
    public String listUploadedFiles(Model model){
        Iterable<Customer> customers = customerService.getCustomers();
        model.addAttribute("allCustomers", customers);
        return "customers";
    }

    @GetMapping("/sortlist")
    public String sortData(ModelMap model, @SortDefault("username") Pageable pageable){
        model.addAttribute("allCustomers", customerService.getCustomersByPage(pageable));
        return "customers";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        storageService.store(file);
        CsvToBean<CustomerInfo> customerInfoCsvToBean=new CsvToBean<CustomerInfo>();
        HeaderColumnNameMappingStrategy<CustomerInfo> strategy = new ColumnPositionMappingStrategy<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(file.getInputStream()));
            String[] columnMapping = {"customer_id","name","date","score","weighting"};
            strategy.setType(CustomerInfo.class);
            List<CustomerInfo> customerInfoList = customerInfoCsvToBean.parse(strategy, reader);
            int idx =0;
            for(CustomerInfo customerInfo : customerInfoList){
//                System.out.println(customerInfo.getCustomerId()+", "+customerInfo.getName() + ", "+customerInfo.getJoinedDate()+ ", "+ customerInfo.getScore() + ","+customerInfo.getWeighting());
                if(idx ==0){
                    idx++;
                    continue;
                }
                String[] names = customerInfo.getName().split(", ");
                Timestamp ts = new Timestamp(convertStringToDate(customerInfo.getJoinedDate()).getTime());
              Integer score =
                        customerInfo.getScore()!=null && !customerInfo.getScore().equals("")
                                ?Integer.valueOf(customerInfo.getScore().trim()):0;
                Double weighting =
                        customerInfo.getWeighting()!=null && !customerInfo.getWeighting().equals("")
                                ?Double.valueOf(customerInfo.getWeighting().trim()):0;
                customerService.addCustomer(
                        customerInfo.getCustomerId().trim(),
                        names.length>0?names[0].trim():"",
                        names.length>1?names[1].trim():"",
                        ts,
                        score,
                        weighting );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/list";
    }

    private Date convertStringToDate(String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        String aMask = "yyyy-MM-dd"; //2017-05-24T21:09:18Z
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

}
