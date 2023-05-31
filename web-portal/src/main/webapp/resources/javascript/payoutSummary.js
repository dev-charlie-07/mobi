function updateDate() {
	const dateElem = document.getElementById("display-time");

	const options = {
		weekday: "long",
		year: "numeric",
		day: "numeric",
		month: "short",

	};

	const date = new Date();
	const formattedDate = date.toLocaleDateString(undefined, options);

	dateElem.textContent = formattedDate.replace(",", "");

}

updateDate();
setInterval(updateDate, 1000);




function dynamic(pgNo) {
	/* alert("Page Number:"+pgNo); */
	alert(pgNo);
	document.getElementById("pgnum").value = pgNo;
	pgSearchByDate();

}

function previous(pgNo) {
	/* alert("Page Number:"+pgNo); */
//	alert(pgNo);
	pgNo--;
	document.getElementById("pgnum").value = pgNo;
	pgSearchByDate();

}

function next(pgNo) {
	/* alert("Page Number:"+pgNo); */
	pgNo++;
	document.getElementById("pgnum").value = pgNo;
	pgSearchByDate();
}


var Pagination = {

	code: '',

	// --------------------
	// Utility
	// --------------------

	// converting initialize data
	Extend: function(data) {
		data = data || {};
		// Pagination.size = data.size || 300; 
		//console.log(Pagination.size);
		// Pagination.size = Math.ceil(${paginationBean.fullCount}/10) ||100;

/*		Pagination.size = 50;
		 Pagination.page = data.page || 1; 
		Pagination.page = 1;
		Pagination.step = ((data.step) - 4) || 3;
		*/
		
		var pageNumber = document.getElementById("pgnum").value;
		alert(pageNumber);
		/* Pagination.page = data.page || 1; */

        Pagination.size = (pageNumber)+4 ||100;
         /* Pagination.page = data.page || 1; */
         Pagination.page = pageNumber || 1;
         Pagination.step = ((data.step)-4) || 3;
         
		Pagination.page = 1;
		Pagination.step = ((data.step) - 4) || 3;
	},

	// add pages by number (from [s] to [f])
	Add: function(s, f) {
		for (var i = s; i < f; i++) {
			Pagination.code += '<a onclick="dynamic(' + i + ')">' + i + '</a>';
		}
	},

	// add last page with separator
	/*   Last: function() {
		  Pagination.code += '<i>...</i>';
	  },
	   */
	Last: function() {
		Pagination.code += '<a onclick="dynamic(((Pagination.page)+1))">' + ((Pagination.page) + 1) + '</a>' + '<a onclick="dynamic(((Pagination.page)+2))">' + ((Pagination.page) + 2) + '</a>' + '<a onclick="dynamic(((Pagination.page)+3))">' + ((Pagination.page) + 3) + '</a>' + '<i>...</i>';
	},

	// add first page with separator
	First: function() {
		if (Pagination.page == 1) {
			Pagination.code += '<i>...</i>' + '<a onclick="dynamic(Pagination.page)">' + Pagination.page + '</a>';

		}
		else {
			Pagination.code += '<a>1</a>' + '<i>...</i>' + '<a onclick="dynamic(((Pagination.page)-1))">' + ((Pagination.page) - 1) + '</a>' + '<a onclick="dynamic(Pagination.page)">' + Pagination.page + '</a>';
		}
	},



	// --------------------
	// Handlers
	// --------------------

	// change page
	Click: function() {
		Pagination.page = +this.innerHTML;
		Pagination.Start();
		dynamic(page);
	},

	// previous page
	Prev: function() {

		Pagination.page--;
		if (Pagination.page < 1) {
			Pagination.page = 1;
		}
		Pagination.Start();
		dynamic(page);
	},



	// next page


	Next: function() {
		Pagination.page++;
		if (Pagination.page > Pagination.size) {
			Pagination.page = Pagination.size;
		}
		Pagination.Start();
		dynamic(page);
	},



	// --------------------
	// Script
	// --------------------

	// binding pages
	Bind: function() {
		var a = Pagination.e.getElementsByTagName('a');
		for (var i = 0; i < a.length; i++) {
			if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
			a[i].addEventListener('click', Pagination.Click, false);
		}
	},

	// write pagination
	Finish: function() {
		Pagination.e.innerHTML = Pagination.code;
		Pagination.code = '';
		Pagination.Bind();
	},

	// find pagination type
	Start: function() {
		if (Pagination.size < Pagination.step * 2 + 6) {
			Pagination.Add(1, Pagination.size + 1);
		}
		else if (Pagination.page < Pagination.step * 2 + 1) {
			Pagination.Add(1, Pagination.step * 2 + 4);
			Pagination.Last();
		}
		else if (Pagination.page > Pagination.size - Pagination.step * 2) {
			Pagination.First();
			Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
		}
		else {
			Pagination.First();
			Pagination.Add(Pagination.page - Pagination.step, Pagination.page + Pagination.step + 1);
			Pagination.Last();
		}
		Pagination.Finish();
	},



	// --------------------
	// Initialization
	// --------------------

	// binding buttons
	Buttons: function(e) {
		var nav = e.getElementsByTagName('a');
		nav[0].addEventListener('click', Pagination.Prev, false);
		nav[1].addEventListener('click', Pagination.Next, false);
	},

	// create skeleton
	Create: function(e) {

		var html = [
			'<a onclick="previous(${paginationBean.currPage})" style="width:6rem;font-weight:bold;font-size:14px;;padding-top:6px;">&#60; Previous</a>', // previous button
			'<span></span>',  // pagination container
			'<a onclick="next(${paginationBean.currPage})" style="width:6rem;font-weight:bold;font-size:14px;padding-top:6px;" id="nxt">Next &#62;</a>'  // next button
		];

		e.innerHTML = html.join('');
		Pagination.e = e.getElementsByTagName('span')[0];
		Pagination.Buttons(e);
	},

	// init
	Init: function(e, data) {
		Pagination.Extend(data);
		Pagination.Create(e);
		Pagination.Start();
	}
};



/* * * * * * * * * * * * * * * * *
* Initialization
* * * * * * * * * * * * * * * * */

var init = function() {
	Pagination.Init(document.getElementById('pagination'), {
		size: 100, // pages size
		page: 1,  // selected page
		step: 3   // pages before and after current
	});
};

document.addEventListener('DOMContentLoaded', init, false);

