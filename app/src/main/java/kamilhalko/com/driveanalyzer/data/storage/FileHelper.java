package kamilhalko.com.driveanalyzer.data.storage;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import kamilhalko.com.driveanalyzer.data.models.Trip;

@Singleton
public class FileHelper {
    private static String fileExtension = ".json";
    private static String directoryTripsName = "trips";
    private static String directorySyncName = "sync";
    private Context context;
    private Gson gson;

    @Inject
    public FileHelper(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    private File createOrOpenTripsDir() {
        return context.getDir(directoryTripsName, Context.MODE_PRIVATE);
    }

    private File createOrOpenSyncDir() {
        return context.getDir(directorySyncName, Context.MODE_PRIVATE);
    }

    public void saveTrip(Trip trip) {
        trip.setId(getNumberOfFiles() + 1);
        writeTripToFile(trip);
    }

    private void writeTripToFile(Trip trip) {
        String fileName = getName(trip);
        String fileContent = gson.toJson(trip);

        File saveFile = new File(createOrOpenTripsDir(), fileName);
        File syncFile = new File(createOrOpenSyncDir(), fileName);

        FileOutputStream saveStream;
        FileOutputStream syncStream;
        try {
            saveStream = new FileOutputStream(saveFile);
            syncStream = new FileOutputStream(syncFile);

            saveStream.write(fileContent.getBytes());
            syncStream.write(fileContent.getBytes());

            saveStream.close();
            syncStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Trip> getTrips() {
        List<File> fileList = getListFiles(createOrOpenTripsDir());
        List<Trip> tripList = new ArrayList<>();
        FileInputStream fin;

        for (File file : fileList) {
            try {
                fin = new FileInputStream(file);
                String fileContent = convertStreamToString(fin);
                tripList.add(gson.fromJson(fileContent, Trip.class));
                fin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tripList;
    }

    public List<File> getNotSynchronizedFiles() {
        return getListFiles(createOrOpenSyncDir());
    }

    private List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(fileExtension)){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private String getName(Trip trip) {
        return trip.getDeviceId() + "_" + trip.getTime() + "_" + trip.getId() + fileExtension;
    }

    private long getNumberOfFiles() {
        return createOrOpenTripsDir().listFiles().length;
    }
}
