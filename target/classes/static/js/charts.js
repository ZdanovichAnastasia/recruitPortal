google.charts.load('current', {'packages':['corechart']});

function drawChart(labels, values) {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    for(i = 0; i < labels.length; ++i){
        data.addRows([labels[i], values[i]]);
    }
    // Set chart options
    var options = {'title':'How Much Pizza I Ate Last Night',
        'width':400,
        'height':300};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}