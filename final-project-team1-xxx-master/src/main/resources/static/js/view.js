/**
 * view.js
 */
export function render(templateId,jsonResult={},contentId) {
	let templateHtml = $(templateId).html();
	let bindTemplate = Handlebars.compile(templateHtml);
	/*****************Handlebars함수등록*************** */
	Handlebars.registerHelper('substring',function(str,start,end){
		return str.substring(start,end);
	});
	
	let resultTemplate = bindTemplate(jsonResult);
	$(contentId).html(resultTemplate);
}
