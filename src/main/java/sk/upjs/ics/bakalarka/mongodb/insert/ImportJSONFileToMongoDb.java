/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.mongodb.insert;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bakalarka.dao.DaoFactory;
import sk.upjs.ics.bakalarka.mongodb.dao.MongoDbReportDao;

/**
 *
 * @author Juraj
 */
public class ImportJSONFileToMongoDb {

    public static String readFile(String pathToFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public static void insertIntoDb(String jsonString) {
        try {
            DBCollection collection = DaoFactory.INSTANCE.getMongoConnection().getCollection("report");
            DBObject object = (DBObject) JSON.parse(jsonString);

            collection.insert(object);

        } catch (IOException ex) {
            Logger.getLogger(MongoDbReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void main(String[] args) throws IOException {
//        insertIntoDb(readFile("C:\\Users\\Juraj\\Desktop\\JSON_DATA.json"));
//    }

}
