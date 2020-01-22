var restify = require('restify');
var externalMethod = require('/home/naveen/node/esTest.js').getFunc;
var Q = require('q');
 

//Call back for end point : /esdata/:name
function getData(req, res, next) {
  console.log("insidegetData")
  console.log(typeof (externalMethod))
  
  //To call method in EsTest file.
   externalMethod().then(function(response){
    console.log("succssfull call back",response)
    //Sending response back to API 
    res.send(response)
   }).catch(function (err){
    console.log(err);
   });
  next();
}

//Server initialisation
var server = restify.createServer();
server.listen(3000, function () {
  console.log('%s%sMain::::::', server.address().address, server.address().port);

});


//End point to get data from ES
server.get('/esdata/:name', getData);

//proces.exit(0);