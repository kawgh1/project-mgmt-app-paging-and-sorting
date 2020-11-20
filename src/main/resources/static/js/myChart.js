// Project Pie Chart

let chartDataString = decodeHtml(chartData);
let chartJsonArray = JSON.parse(chartDataString);

let arrayLength = chartJsonArray.length;
let numericData = [];
let labelData = [];

for(let i=0; i<arrayLength; i++) {
    // populate data arrays for our JSChart to read from the Json Strings of returned database query data
    numericData[i] = chartJsonArray[i].value; // numericData = [1,2,1]
    labelData[i] = chartJsonArray[i].label;     // labelData = ["COMPLETED", "INPROGRESS", "NOTSTARTED"]
}

// pie chart at www.chartjs.org
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // data from our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First Dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#546e7a", "#90a4ae", "#A1887f", "#ff8a65", "#ffb74d", "#fff176"],
            borderColor: 'whitesmoke',
            data: numericData
        }]
    },

    //configuration options go here
    options: {
        responsive: true,
        title: {
            display: true,
            text: 'Project Status'
        }
    }
});

// Employee Pie CHart

let employeeChartDataString = decodeHtml(employeeChartData);
let employeeChartJsonArray = JSON.parse(employeeChartDataString);

let empArrayLength = employeeChartJsonArray.length;
let empNumericData = [];
let empLabelData = [];

for(let i=0; i<empArrayLength; i++) {
    // populate data arrays for our JSChart to read from the Json Strings of returned database query data
    empNumericData[i] = employeeChartJsonArray[i].value;
    empLabelData[i] = employeeChartJsonArray[i].label;
}

// pie chart at www.chartjs.org
new Chart(document.getElementById("employeePieChart"), {
    type: 'pie',
    // data from our dataset
    data: {
        labels: empLabelData,
        datasets: [{
            label: 'My First Dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#546e7a", "#90a4ae", "#A1887f", "#ff8a65", "#ffb74d", "#fff176"],
            borderColor: 'whitesmoke',
            data: empNumericData
        }]
    },

    //configuration options go here
    options: {
        responsive: true,
        title: {
            display: true,
            text: 'Projects per Employee'
        }
    }
});

// Manager Pie Chart

let managerChartDataString = decodeHtml(managerChartData);
let managerChartJsonArray = JSON.parse(managerChartDataString);

let mgrArrayLength = managerChartJsonArray.length;
let mgrNumericData = [];
let mgrLabelData = [];

for(let i=0; i<mgrArrayLength; i++) {
    // populate data arrays for our JSChart to read from the Json Strings of returned database query data
    mgrNumericData[i] = managerChartJsonArray[i].value;
    mgrLabelData[i] = managerChartJsonArray[i].label;
}

// pie chart at www.chartjs.org
new Chart(document.getElementById("managerPieChart"), {
    type: 'pie',
    // data from our dataset
    data: {
        labels: mgrLabelData,
        datasets: [{
            label: 'My First Dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#546e7a", "#90a4ae", "#A1887f", "#ff8a65", "#ffb74d", "#fff176"],
            borderColor: 'whitesmoke',
            data: mgrNumericData
        }]
    },

    //configuration options go here
    options: {
        responsive: true,
        title: {
            display: true,
            text: 'Projects per Manager'
        }
    }
});



// Decode raw HTML from backend into readable JSON in the HTML template
// String "[{"value": 1, "label": "COMPLETED"}, {"value": 2, "label": "INPROGRESS"}, {"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html){
    let txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}


// example for pie chart at www.chartjs.org
// new Chart(document.getElementById("myPieChart"), {
//     type: 'pie',
//     // data from our dataset
//     data: {
//         labels: ['January', 'February', 'March'],
//         datasets: [{
//             label: 'My First Dataset',
//             backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
//             borderColor: 'rgb(255, 99, 132)',
//             data: [5, 10, 15]
//         }]
//     },
//
//     //configuration options go here
//     options: {}
// });