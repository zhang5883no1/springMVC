<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinShan
  Date: 2016/8/25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  lang="en">
<head>
    <meta charset="UTF-8">
    <title>白银基差</title>
    <script src="/static/js/echarts.js"></script>
</head>
<body>
<div id="main"style="width:720px;height:290px;border:1px solid #ccc;padding:10px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));

    // 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
    var data0 = splitData([
            <c:forEach items="${priceList}" var="u"><%--遍历priceList集合或数组，每次迭代使用变量u代表其中的元素--%>
            <%--<div class="col-md-2">${u}</div>--%>
            ${u}
            </c:forEach>
    ]);


    function splitData(rawData) {
        var categoryData = [];
        var values = []
        for (var i = 0; i < rawData.length; i++) {
//            categoryData.push(rawData[i].splice(0, 1)[0].substring(6,rawData[i].splice(0, 1)[0].length));
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i])
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = data0.values.length; i < len; i++) {
            if (i < dayCount) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < dayCount; j++) {
                sum += data0.values[i - j][1];
            }
            result.push(sum / dayCount);
        }
        return result;
    }



    option = {
        title: {
            text: '白银基差',
            left: 0
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            //data: ['5分钟', '5均线', '10均线', '20均线', '30均线']
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: {
            type: 'category',
            data: data0.categoryData,
            scale: true,
            boundaryGap : false,
            axisLine: {onZero: false},
            splitLine: {show: false},
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax'
        },
        yAxis: {
            scale: true,
            splitArea: {
                show: true
            }
        },
        dataZoom: [
            {
                type: 'inside',
                start: 50,
                end: 100
            },
            {
                show: true,
                type: 'slider',
                y: '90%',
                start: 50,
                end: 100
            }
        ],
        series: [
            {
                name: '5分钟K线',
                type: 'candlestick',
                data: data0.values,
                markPoint: {
                    label: {
                        normal: {
                            formatter: function (param) {
                                return param != null ? Math.round(param.value) : '';
                            }
                        }
                    },
                    data: [
                        /*{
                            name: 'XX标点',
                            coord: ['2013/5/31', 2300],
                            value: 2300,
                            itemStyle: {
                                normal: {color: 'rgb(41,60,85)'}
                            }
                        },
                        {
                            name: 'highest value',
                            type: 'max',
                            valueDim: '最高'
                        },
                        {
                            name: 'lowest value',
                            type: 'min',
                            valueDim: '最低'
                        },
                        {
                            name: 'average value on close',
                            type: 'average',
                            valueDim: '收盘'
                        }*/
                    ],
                    tooltip: {
                        formatter: function (param) {
                            return param.name + '<br>' + (param.data.coord || '');
                        }
                    }
                },
                markLine: {
                    symbol: ['none', 'none'],
                    data: [
                        [
                            {
                                /*name: 'from lowest to highest',
                                type: 'min',
                                valueDim: '最低',*/
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            },
                            {
                                /*type: 'max',
                                valueDim: '最高',*/
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            }
                        ],
                        /*{
                            name: 'min line on close',
                            type: 'min',
                            valueDim: '收盘'
                        },
                        {
                            name: 'max line on close',
                            type: 'max',
                            valueDim: '收盘'
                        }*/
                    ]
                }
            },
            {
                name: '5均线',
                type: 'line',
                data: calculateMA(5),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: '10均线',
                type: 'line',
                data: calculateMA(10),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: '20均线',
                type: 'line',
                data: calculateMA(20),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: '30均线',
                type: 'line',
                data: calculateMA(30),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },

        ]
    };



    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>
</html>
