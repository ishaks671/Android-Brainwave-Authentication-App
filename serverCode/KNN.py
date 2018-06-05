#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import pandas as pd
from sklearn import preprocessing
from sklearn.cross_validation import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score
import sys
testFile = sys.argv[1]

data = pd.read_csv('newData.csv', header = None)
test = pd.read_csv(testFile, header = None)
label = list(data[data.shape[1]-1])
testL = list(test[test.shape[1]-1])
testLabelContent = []
for i in testL:
    if i not in testLabelContent:
        testLabelContent.append(i)
data = data.drop(data.shape[1]-1,axis = 1)
test = test.drop(test.shape[1]-1,axis = 1)

data = preprocessing.scale(data)
test = preprocessing.scale(test)

trainData, testData, trainLabel, testlabel = train_test_split(
        data, label, test_size = 0, random_state = 10)

model = KNeighborsClassifier(n_neighbors = 7).fit(trainData, trainLabel)
prediction = model.predict(test)

count = 0
for i in prediction:
    if i in testLabelContent:
        count = count + 1
accuracy = accuracy_score(testL, prediction, normalize = True)
if count > 0:
    print('1')
