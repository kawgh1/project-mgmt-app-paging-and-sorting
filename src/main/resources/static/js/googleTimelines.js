// projectTimeList from projectController


    let chartData = "[[${projectTimeList}]]";
    let chartDataDecoded = decodeHtml(chartData);
    let chartJsonArray = JSON.parse(chartDataDecoded);
    let resultArray = [];


    for(let i in chartJsonArray){
    resultArray.push([i, chartJsonArray[i]]);
}

    google.charts.load('current', {'packages':['timeline']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
    let container = document.getElementById('chart_div');
    let chart = new google.visualization.Timeline(container);
    let dataTable = new google.visualization.DataTable();

    dataTable.addColumn({ type: 'string', id: 'Project' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });

    for (let i = 0; i < resultArray.length; i++) {
    dataTable.addRows([
    [resultArray[i][1]["projectName"],
    new Date(resultArray[i][1]["startDate"]),
    new Date(resultArray[i][1]["endDate"])]
    ]);
}


    chart.draw(dataTable);


}



    function decodeHtml(html) {
    let txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}

