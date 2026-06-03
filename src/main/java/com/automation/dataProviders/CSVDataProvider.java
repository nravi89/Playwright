package com.automation.dataProviders;

import com.automation.annotations.DataProviderFilePath;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CSVDataProvider {

    @DataProvider(name="csvReader")
    public Iterator<Object[]> csvData(Method method){
        List<Object[]> data = new ArrayList();
        String filePath = method.getAnnotation(DataProviderFilePath.class).value();
        Class[] types = method.getParameterTypes();
        Path path = null;
        try {
            path = Path.of(getClass().getClassLoader().getResource(filePath).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        boolean ignoreFirstHeaderLine = false;
        // The try-with-resources statement ensures the file stream closes automatically
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1).forEach(line->{
                String[] values = line.split(",");
                data.add(IntStream.range(0, values.length)
                        .mapToObj(i->valueOf(values[i].trim(),types[i]))
                        .toArray());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.iterator();
    }

    private static Object valueOf(String value, Class type){

        //System.out.println("==>"+type+","+type.getTypeName());

        if(type.getTypeName().equals(Integer.class.getTypeName()) || type.getTypeName().equals(int.class.getTypeName())){
            return Integer.valueOf(value);
        }
        if(type.getTypeName().equals(Double.class.getTypeName()) || type.getTypeName().equals(double.class.getTypeName())){
            return Double.valueOf(value);
        }
        if(type.getTypeName().equals(Boolean.class.getTypeName()) || type.getTypeName().equals(boolean.class.getTypeName())){
            return Boolean.valueOf(value);
        }

        return value;
    }
}
