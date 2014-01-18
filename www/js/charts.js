function loadGestureChartsData() {
        $('#gestureChart').highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: 'Gestures and Clicks'
            },
            subtitle: {
                text: 'Gestures and clicks over time.'
            },
            xAxis: {
                type: 'datetime',
                labels: {
                    overflow: 'justify'
                }
            },
            yAxis: {
                title: {
                    text: 'Clicks & Gestures'
                },
                min: 0,
                minorGridLineWidth: 0,
                gridLineWidth: 0,
                alternateGridColor: null,
                plotBands: [{
                    from: 0.3,
                    to: 3.3,
                    color: 'rgba(68, 170, 213, 0.1)',
                    }, {
                    from: 3.3,
                    to: 5.5,
                    color: 'rgba(68, 170, 213, 0.1)',
                }, {
                    from: 5.5,
                    to: 8,
                    color: 'rgba(0, 0, 0, 0)',
                }, {
                    from: 8,
                    to: 11,
                    color: 'rgba(68, 170, 213, 0.1)',
                }, {
                    from: 11,
                    to: 14,
                    color: 'rgba(0, 0, 0, 0)',
                }, {
                    from: 14,
                    to: 15,
                    color: 'rgba(68, 170, 213, 0.1)',
                }]
            },
            tooltip: {
                valueSuffix: ' per min.'
            },
            plotOptions: {
                spline: {
                    lineWidth: 4,
                    states: {
                        hover: {
                            lineWidth: 5
                        }
                    },
                    marker: {
                        enabled: false
                    },
                    pointInterval: 3600000, // one hour
                    pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
                }
            },
            series: [{
                name: 'Clicks',
                data: [4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
                    7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
                    8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
                    7.1, 7.5, 8.1, 6.8, 3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2,
                    3.0, 3.0]
    
            }, {
                name: 'Voll',
                data: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
                    0.0, 0.4, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                    0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
                    3.0, 3.3, 4.8, 5.0, 4.8, 5.0, 3.2, 2.0, 0.9, 0.4, 0.3, 0.5, 0.4]
            }]
            ,
            navigation: {
                menuItemStyle: {
                    fontSize: '10px'
                }
            }
        });
}


$(document).ready(
  function() {
    loadGestureChartsData();
  }
);