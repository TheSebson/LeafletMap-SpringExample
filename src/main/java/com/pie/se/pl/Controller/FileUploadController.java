package com.pie.se.pl.Controller;

import com.pie.se.pl.Dao.AggregateDAO;
import com.pie.se.pl.Dao.MarkerDAO;
import com.pie.se.pl.Model.Aggregate;
import com.pie.se.pl.Model.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Controller
public class FileUploadController {

    @Autowired
    public AggregateDAO aggregateDAO;
    @Autowired
    public MarkerDAO markerDAO;

    private List<String[]> records = new ArrayList();

    private Set<String> aggregation = new TreeSet();

    @RequestMapping("/")
    public String UploadPage(Model model) {
        return "index";
    }

    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            BufferedReader br;

            try {
                String line;
                InputStream is = file.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    records.add(words);
                    aggregation.add(words[3]);
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        for (String item : aggregation)
            setList(item);
        return "index";
    }

    private void setList(String agregateBy) {
        Aggregate agregation = new Aggregate(agregateBy);
        for (String[] line : records) {
            if (line[3].equals(agregateBy)) {
                Marker item = new Marker((Double.parseDouble(line[1])*180.0/Math.PI), (Double.parseDouble(line[2])*180.0/Math.PI), line[0]);
                agregation.addToList(item);
                markerDAO.save(item);
            }
        }
        aggregateDAO.save(agregation);
    }

}
