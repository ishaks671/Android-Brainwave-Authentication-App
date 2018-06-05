// Modules
const express = require('express');
const upload = require('express-fileupload');
const { spawn } = require('child_process');
var http = require("http");

var app = express();
app.use(upload());

app.get("/server/:algo&:user", (req, res) => {
        //res.send('Connected to Cloud Backend Server!')

        if (req.params.algo !=0) {
          // Call python script to process the file data
                console.log("Running Algorithm : "+req.params.algo)

                if(req.params.algo==1) {
                  var script_path = '/Users/aravind/Desktop/server/naiveBayes.py';
                }
                else if(req.params.algo==2) {
                  var script_path = '/Users/aravind/Desktop/server/SVM.py';
                }
                else if(req.params.algo==3) {
                  var script_path = '/Users/aravind/Desktop/server/KNN.py';
                }
                else if(req.params.algo==4) {
                  var script_path = '/Users/aravind/Desktop/server/logisticRegression.py';
                }
                else if(req.params.algo==5) {
                  var script_path = '/Users/aravind/Desktop/server/decisionTree.py';
                }



                if(req.params.user=="aravind") {
                  var user_path = '/Users/aravind/Desktop/server/aravind.csv';
                }
                else if(req.params.user=="giriraj") {
                  var user_path = '/Users/aravind/Desktop/server/giriraj.csv';
                }
                else if(req.params.user=="kasi") {
                  var user_path = '/Users/aravind/Desktop/server/kasi.csv';
                }
                else if(req.params.user=="naveen.csv") {
                  var user_path = '/Users/aravind/Desktop/server/naveen.csv';
                }
                else if(req.params.user=="nidhi") {
                  var user_path = '/Users/aravind/Desktop/server/nidhi.csv';
                }
                else if(req.params.user=="sai") {
                  var user_path = '/Users/aravind/Desktop/server/sai.csv';
                }
                else if(req.params.user=="sarvesh") {
                  var user_path = '/Users/aravind/Desktop/server/sarvesh.csv';
                }
                else if(req.params.user=="venky") {
                  var user_path = '/Users/aravind/Desktop/server/venky.csv';
                }
                else {
                  res.send('User not present');
                  console.log('User not present');
                }


                const pyProg = spawn('python', [script_path, user_path]);

                pyProg.stdout.on('data', (data) => {
                        res.send(data);
                        console.log('Accuracy is : ' + `${data}`);
                });

                pyProg.on('exit', function (code, signal) {
                        console.log('child process exited with ' + `code ${code} and signal ${signal}`);
                });
        }
        else{
          res.send('Algorithm not present');
          console.log('Algorithm not present');
        }
});
app.listen(3000, () => console.log('Server running on port 3000'))