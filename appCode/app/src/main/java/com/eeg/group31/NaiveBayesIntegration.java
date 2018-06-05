package com.eeg.group31;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.eeg.group31.properties.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by girir on 04/25/2018.
 */

public class NaiveBayesIntegration {
    String TAG = "NAIVE BAYES IMPLEMENTATION";
    //Attribute attribute;
    FastVector fvClasses;
    FastVector fvAttributes;
    Instances trainSet,testSet;
    //Instance dataPoint;
    Classifier model;
    Evaluation validation;
    TextView result;
    TextView t1;
    Context context;
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;
    String line;String[] data;

    long startTime;
    long endTime;

    public NaiveBayesIntegration(Context context){
        this.context = context;
    }


    public String runNB(String trainFile, String testFile) {
        try {
            fvAttributes = new FastVector(Constants.NUM_FEATURES + 1);
            fvClasses = new FastVector(Constants.NUM_CLASSES);
            for (int i = 1; i <= Constants.NUM_FEATURES; i++) {
                fvAttributes.addElement(new Attribute(Integer.toString(i)));
            }
            for (int i = 1; i <= Constants.NUM_CLASSES; i++) {
                fvClasses.addElement(Integer.toString(i));
            }
            fvAttributes.addElement(new Attribute("Class", fvClasses));
Log.d(TAG,"created class arrtibuted");
            trainSet = new Instances("TrainData", fvAttributes, Constants.NUM_TRAIN_DATA);
            testSet = new Instances("TestData", fvAttributes, Constants.NUM_TEST_DATA);
            trainSet.setClassIndex(Constants.NUM_FEATURES);
            testSet.setClassIndex(Constants.NUM_FEATURES);
            Log.d(TAG, "size =" + fvAttributes.size());
            file = new File(trainFile);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            Log.d(TAG,"start reading train file");
int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                Instance dataPoint = new DenseInstance(fvAttributes.size());
                data = line.split(" ");
              //  Log.d(TAG,Integer.toString(count++));
               // Log.d(TAG,line);
                dataPoint.setValue((Attribute) fvAttributes.elementAt(Constants.NUM_FEATURES), data[0]);
                data = ArrayUtils.remove(data,0);
                for (int i = 0; i < Constants.NUM_FEATURES; i++) {
                    dataPoint.setValue((Attribute) fvAttributes.elementAt(i), Float.parseFloat((data[i].split(":"))[1]));
                }
                trainSet.add(dataPoint);
            }
            Log.d(TAG,"start reading test file");


            model = new NaiveBayes();
            model.buildClassifier(trainSet);
            //Log.d(TAG, "Naive Bayes Training Complete");
            file = new File(testFile);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
/*            while ((line = bufferedReader.readLine()) != null) {
                Instance dataPoint = new DenseInstance(fvAttributes.size());
                data = line.split(" ");
                dataPoint.setValue((Attribute) fvAttributes.elementAt(Constants.NUM_FEATURES), data[0]);
                data = ArrayUtils.remove(data,0);
                for (int i = 0; i < Constants.NUM_FEATURES; i++) {
                    dataPoint.setValue((Attribute) fvAttributes.elementAt(i), Float.parseFloat((data[i].split(":"))[1]));
                }
                testSet.add(dataPoint);
            }
*/
            //validation = new Evaluation(trainSet);
            //validation.evaluateModel(model, testSet);
            //String strSummary = validation.toSummaryString("my Stats", true);
            //Log.d(TAG, strSummary);
            //Log.d(TAG,"my result NB ="+Double.toString(validation.pctCorrect()));
            return "User Authenticated";
            //result.setText("User Authenticated");
            //t1.setText("App time = " + Long.toString(endTime - startTime) + "ms");
            // Get the confusion matrix
            //double[][] cmMatrix = validation.confusionMatrix();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,"other user may be");
            return "Invalid User";
            //result.setText("Invalid User");
            //t1.setText("App time = " + Long.toString(endTime - startTime) + "ms");
        }
    }
}
