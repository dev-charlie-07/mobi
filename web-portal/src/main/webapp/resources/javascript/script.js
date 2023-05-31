        const displayTime = document.querySelector(".display-time");
         // Date
         function updateDate() {
           let today = new Date();
           // return number
           let dayName = today.getDay(),
             dayNum = today.getDate(),
             month = today.getMonth(),
             year = today.getFullYear();
           const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", ];
           const dayWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", ];
           // value -> ID of the html element
           const IDCollection = ["day", "daynum", "month", "year"];
           // return value array with number as a index
           const val = [dayWeek[dayName], dayNum, months[month], year];
           for (let i = 0; i < IDCollection.length; i++) {
             document.getElementById(IDCollection[i]).firstChild.nodeValue = val[i];
           }
         }
         updateDate();
		 
		 
		 
		 
		 
 