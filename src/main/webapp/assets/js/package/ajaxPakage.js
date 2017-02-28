// 封装ajax
define(['jquery', 'bootstrap'], function($) {
  var base = {
    // url: config.url,
    type: 'post',
    dataType: 'json',
    data: {},
    timeout: 30000,
    cache: false,
    async: true
  };
  var Ajax = {
    request: function(config) {
      $.extend(base, config);
      $.ajax(base);
    }
  }
  return {
    'Ajax': Ajax
  }
});