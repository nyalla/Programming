var restify = require('restify');
var request = require('request');
var ld = require('lodash');
var fs = require("fs");

function respond(req, res, next) {
  //res.send('hello ' + req.params.name);
  fs.readFile(__dirname + "/" + "users.json", 'utf8', function (err, data) {
    //console.log( data );
    // console.log( typeof(data));
    var obj = JSON.parse(data);
    var keys = Object.keys(obj);
    const re1 = new RegExp('password')
    for (var i = 0; i < keys.length; i++) {
      console.log(ld.pick(obj[keys[i]], ['name']));
      if (re1.test('password')) {
        console.log("Pass word exists for : ", obj[keys[i]].name)
      }
      if (obj[keys[i]].name == "ramesh") {
        //console.log(obj[keys[i]].name);
        //var object = { 'a': 1, 'b': '2', 'c': 3 };
        console.log(ld.pick(obj[keys[i]], ['name']));
      }

    }
    res.end(data);
  });
  next();
}

var server = restify.createServer();
// request('http://www.google.com', function (error, response, body) {
//     if (!error && response.statusCode == 200) {
//         console.log(body) // Print the google web page.
//      }
// })
server.get('/hello/:name', respond);
//server.head('/hello/:name', respond);

server.listen(8080, function () {
  console.log('%s listening at %s', server.name, server.url);
});


//proces.exit(0);