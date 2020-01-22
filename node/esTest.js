var elasticsearch = require('elasticsearch');
var _ = require('lodash')
var q = require ('q')

//if (require.main == module) {
module.exports = {
  getFunc: function ()
{

    var defer = q.defer();

    console.log("inside es test file ")

    // Getting connection from ES instance
    var esClient = new elasticsearch.Client({
      host: '10.0.2.2:9200',
      log: 'error'
    });


    //Checking ES instance ping
    esClient.ping({
      // ping usually has a 3000ms timeout
      requestTimeout: 1000
    }, function (error) {
      if (error) {
        console.log('Down!');
      } else {
        console.log('Up');
      }
    });


//constructing ES query
    var metricsQuery = {
      index: 'localazuremetric',
      type: 'utilisationMetrics',
      body: {
        "query": {
          "bool": {
            "must": [
              {
                "range": {
                  "timeStamp": {
                    "gte": 1569110400000,
                    "lte": 1569196740000
                  }
                }
              },
              {
                "term": {
                  "component.keyword": {
                    "value": "Percentage CPU"
                  }
                }
              },
              {
                "term": {
                  "deviceName.normalize": {
                    "value": "/subscriptions/be378aa8-a29c-4fcc-a8d0-814b219202c2/resourcegroups/diva-portal/providers/microsoft.compute/virtualmachines/inspire-test-vm5"
                  }
                }
              },
              {
                "term": {
                  "type.keyword": {
                    "value": "maximum"
                  }
                }
              }
            ]
          }
        },
        "aggs": {
          "byday": {
            "date_histogram": {
              "field": "timeStamp",
              "interval": "20m"

            },
            "aggs": {
              "NAME": {
                "max": {
                  "field": "utilization"
                }
              }
            }
          }
        },
        "size": 0
      }
    };

    var finalDataArray = []
    //console.log("asdsa")

    //Queying the ES with given query
    esClient.search(metricsQuery).then(function (resp) {
      var hits = resp.hits.hits;
      //console.log(resp.aggregations.byday.buckets)
      var finalData = null;
      
      _.each(resp.aggregations.byday.buckets, function (data) {
        //finalData=_.pick(data, ['key','NAME.value'])
        finalDataArray.push({
          'timeStamp': data.key,
          'value': data.NAME.value
        }
        )
      })
      //console.log(finalDataArray)
      defer.resolve(finalDataArray);
    }, function (err) {
      console.error(err);
      defer.reject(err);
    }); 
     
      return defer.promise;
  }
}
  //process.exit(0);

//}