$(function() {
	$(document).on("click", ".search-results, .search-results li", function(event) {
		// 이벤트가 발생한 대상이 ul이나 li가 아닌 경우
		$(".search-results").empty();
		$('.job-search-form form, .banner-section-four .job-search-form form').css({
			'border-bottom-left-radius': '50px',
			'border-bottom-right-radius': '50px'
		});
	});
	
	function search(query, searchResults) {
		return searchResults.filter(item => item.searchData.toLowerCase().includes(query.toLowerCase()));
	}

	// 검색 결과 업데이트
	function updateSearchResults(results) {
		const searchResults = $(".search-results");
		searchResults.empty();
		results.forEach(result => {
			const listItem = $("<li>").text(result.searchData);
			listItem.on("click", function() {
				$("#searchInput").val($(this).text());
				$('.job-search-form form, .banner-section-four .job-search-form form').css({
					'border-bottom-left-radius': '50px',
					'border-bottom-right-radius': '50px'
				});
			});
			searchResults.append(listItem);
		});
	}

	// input 이벤트 리스너
	$("#searchInput").on("input", function() {
		const query = $(this).val();
		if (query.trim() === "") {
			// searchInput의 값이 없을 경우, search-results 내의 모든 li를 삭제
			$(".search-results").empty();
			$('.job-search-form form, .banner-section-four .job-search-form form').css({
				'border-bottom-left-radius': '50px',
				'border-bottom-right-radius': '50px'
			});
		} else {
			const jsonData = {
				searchSelect: $(".search-select").val(),
				keyword: query
			}
			let promise = $.ajax({
				type: 'POST',
				url: 'keywordSearch',
				data: JSON.stringify(jsonData),
				contentType: 'application/json',
				dataType: 'json'
			});

			// Promise 객체를 사용하여 Ajax 요청 처리
			promise.then(function(response) {
				const searchResults = response.results
				const results = search(query, searchResults);
				updateSearchResults(results);
				$('.job-search-form form, .banner-section-four .job-search-form form').css({
					'border-bottom-left-radius': '0',
					'border-bottom-right-radius': '0'
				});
								
			})
			.fail(function(xhr) {
				window.location.href="/final-project-ilhajob/errorPage";
			});
		}
		
	});
	
	$("#searchInput").on("click focus", function() {
			$(this).trigger('input');
	});
	
	
	
});
