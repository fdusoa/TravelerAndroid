/**
 * Created by duocai on 2017/5/8.
 */

var soap = require('soap');
var url = 'http://120.76.125.35/axis2/services/UserService?wsdl';
var args = {
  username: 'admin',
  password: 'admin2'
};

soap.createClient(url, function(err, client) {
  client.register(args, function(err, result) {
    console.log(err);
    console.log(result);
  });
});
