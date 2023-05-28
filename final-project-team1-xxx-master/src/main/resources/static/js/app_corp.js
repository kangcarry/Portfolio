import * as View from "./view.js";
import * as Request from "./request.js";
Handlebars.registerHelper('ifEqual', function(v1, v2, options) {
  return (v1 === v2) ? options.fn(this) : options.inverse(this);
});
Handlebars.registerHelper('ifEquals', function(a, b, options) {
	    if (a == b) {
	        return options.fn(this);
	    } else {
	        return options.inverse(this);
	    }
	});
Handlebars.registerHelper('dateFormat', function(format, date) {
  var momentDate = moment(date);
  return momentDate.format(format);
});
Handlebars.registerHelper('eq', function(a, b) {
	  return a === b;
});
Handlebars.registerHelper('not', function(value) {
    return !value;
});
Handlebars.registerHelper('subtract', function(a, b) {
    return a - b;
});
Handlebars.registerHelper('add', function(a, b) {
    return a + b;
});
Handlebars.registerHelper('gte', function(a, b) {
    return a >= b;
});
Handlebars.registerHelper('lte', function(a, b) {
    return a <= b;
});
Handlebars.registerHelper('and', function(a, b) {
  return a && b;
});
Handlebars.registerHelper('formatDate', function(date, format) {
	console.log("formatDate도착");
  return temporals.format(date, format);
});
Handlebars.registerHelper('lookup', function (obj, key) {
		 if (obj === undefined) {
		        return ""; // 또는 적절한 기본값 또는 에러 처리 로직을 추가하세요
		    }
	        return obj[key];
	 });
Handlebars.registerHelper('with', function(context, options) {
  return options.fn(context);
});

$('.changeTag').click(function(e){
	let sendData={
		tagId:parseInt($(this).val())
		
	};
	console.log(sendData);
	Request.ajaxRequest('getTagData',
					    'GET',
						'application/json;charset=UTF-8',
						sendData,
						function(resultJson){
							console.log("최종 결과 데이터",resultJson);
							View.render("#tag-filter-template",resultJson,"#corp-main-list");
						},
						true); //비동기
	e.preventDefault();
});
$(document).on('click','.next-link',function(e){
	e.preventDefault();
	let sendData={
		tagId:parseInt($(".tag-data").val()),
		page:parseInt($(".next-data").val())
	};
	console.log(sendData);
	Request.ajaxRequest('getTagData',
					    'GET',
						'application/json;charset=UTF-8',
						sendData,
						function(resultJson){
							View.render("#tag-filter-template",resultJson,"#corp-main-list");
							window.scrollTo(0, 0);
						},
						true);

});

$(document).on('click','.prev-link',function(e){
	e.preventDefault();
	let sendData={
		tagId:parseInt($(".tag-data").val()),
		page:parseInt($(".prev-data").val())
	};
	console.log(sendData);
	Request.ajaxRequest('getTagData',
					    'GET',
						'application/json;charset=UTF-8',
						sendData,
						function(resultJson){
							View.render("#tag-filter-template",resultJson,"#corp-main-list");
							window.scrollTo(0, 0);
						},
						true);

});


$(document).ready(function() {
	  $('#searchInput').on('input', function() {
	    var corpName = $('#searchInput').val();
	    var job = $('select[name="job"]').val();
	    $.ajax({
	      url: '/search',
	      method: 'GET',
	      data: {
	        corpName: corpName,
	        job: job
	      },
	      success: function(data) {
	        var results = data.results;
	        var rcCountMap = data.rcCountMap;
	        var template = Handlebars.compile($('#searchTemplate').html());
	        if (results.length > 0) {
	          $('#searchResults').html(template({ results: results, rcCountMap: rcCountMap }));
	        } else {
	          $('#searchResults').html('');
	          $('#searchResults').append('<div class="alert alert-warning">검색 결과가 없습니다.</div>');
	        }
	      }
	    });
	  });
	});
