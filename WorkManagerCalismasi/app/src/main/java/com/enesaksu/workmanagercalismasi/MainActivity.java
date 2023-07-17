package com.enesaksu.workmanagercalismasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder().putInt("intKey",1).build();

        Constraints constraints = new Constraints.Builder()
                //.setRequiredNetworkType(NetworkType.CONNECTED)//İnternete bağlı olduğunda çalış..
                .setRequiresCharging(false)
                .build();

        /* Sadece Bir Kere Çalışır ...
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
               // .setInitialDelay(5, TimeUnit.MINUTES) //%dk sonra çalış
                //.addTag("myTag")
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);
         */

        //Periyodik olarak çalışır..
        //En az 15 dk da bir çalışması gerek.
        WorkRequest workRequest = new PeriodicWorkRequest.Builder(RefreshDatabase.class,15,TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {// Çalışıp çalışmadığını gösteriyo ona göre işlemler yapabiliriz.
                if (workInfo.getState() == WorkInfo.State.RUNNING){
                    System.out.println("Running");
                }else if(workInfo.getState() == WorkInfo.State.SUCCEEDED){
                    System.out.println("Succeeded");
                }
                else if(workInfo.getState() == WorkInfo.State.FAILED){
                    System.out.println("failed");
                }
            }
        });

        //WorkManager.getInstance(this).cancelAllWork();

        //Chaining **Aynanda birbirine bağlı bir şekilde çalıştırabiliyoruz**
        /*
        OneTimeWorkRequest oneTimeWorkRequest = new WorkRequest.Builder(RefreshDatabase.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)
                .enqueue();
         */




    }
}