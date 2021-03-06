sap.ui.define([
        'jquery.sap.global',
        'sap/ui/core/mvc/Controller',
        'sap/ui/model/json/JSONModel',
        'sap/viz/ui5/data/FlattenedDataset',
        'sap/viz/ui5/controls/common/feeds/FeedItem',
        './CustomerFormat',
        './InitPage'
    ], function(jQuery, Controller, JSONModel, FlattenedDataset, FeedItem, CustomerFormat, InitPageUtil) {
    "use strict";

    var Controller = Controller.extend("sap.viz.sample.TimeAxis.TimeAxis", {

        dataPath : "test-resources/sap/viz/demokit/dataset/milk_production_testing_data/date_revenue_cost",

        settingsModel : {
            chartType : {
                name : "Chart Type",
                defaultSelected : "3",
                values : [{
                    key : "0",
                    name : "Bubble Chart",
                    vizType : "timeseries_bubble",
                    json : "/bubble/medium.json",
                    value : ["Cost"],
                    dataset : {
                        "dimensions": [{
                            "name": "Date",
                            "value": "{Date}",
                            "dataType":"date"
                        }],
                        "measures": [{
                            "name": "Cost",
                            "value": "{Cost}"
                        },
                        {
                            "name": "Revenue",
                            "value": "{Revenue}"
                        }],

                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            }
                        },
                        valueAxis: {
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        categoryAxis: {
                            title: {
                                visible: true
                            }
                        },
                        sizeLegend: {
                            formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                            title: {
                                visible: true
                            }
                        },
                        title: {
                            visible: false
                        }
                    }
                },{
                    key : "1",
                    name : "Column Chart",
                    vizType : "timeseries_column",
                    json : "/column/medium.json",
                    value : ["Cost"],
                    dataset : {
                       dimensions: [{
                           name: 'Date',
                           value: "{Date}",
                           dataType:'date'
                       }],
                       measures: [{
                           name: 'Cost',
                           value: '{Cost}'
                       }],
                       data: {
                           path: "/milk"
                       }
                    },
                    vizProperties : {
                        plotArea: {
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                        },
                        valueAxis: {
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        title: {
                            visible: false
                        }
                    }
                },{
                    key : "2",
                    name : "Column Chart with multiple Series",
                    vizType : "timeseries_column",
                    json : "/timeBulletStacked.json",
                    value : ["Cost2", "Cost1"],
                    dataset : {
                       dimensions: [{
                           name: 'Date',
                           value: "{Date}",
                           dataType:'date'
                       }],
                       measures: [{
                           name: 'Cost2',
                           value: '{Cost2}'
                       },{
                           name: 'Cost1',
                           value: '{Cost1}'
                       }],
                       data: {
                           path: "/milk"
                       }
                    },
                    vizProperties : {
                        plotArea: {
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                        },
                        valueAxis: {
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        title: {
                            visible: false
                        }
                    }
                },{
                    key : "3",
                    name : "Line Chart",
                    vizType : "timeseries_line",
                    json : "/column/timeAxis.json",
                    value : ["Revenue"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Revenue',
                            value: '{Revenue}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            }
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "4",
                    name : "Line Chart with Dynamic Value Axis",
                    vizType : "timeseries_line",
                    json : "/column/timeAxis.json",
                    value : ["Revenue"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Revenue',
                            value: '{Revenue}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            }
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: true
                        }
                    }
                },{
                    key : "5",
                    name : "Scatter Chart",
                    vizType : "timeseries_scatter",
                    json : "/column/large.json",
                    value : ["Cost"],
                    dataset : {
                       dimensions: [{
                           name: 'Date',
                           value: "{Date}",
                           dataType:'date'
                       }],
                       measures: [{
                           name: 'Cost',
                           value: '{Cost}'
                       }],
                       data: {
                           path: "/milk"
                       }
                    },
                    vizProperties : {
                        plotArea: {
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            }
                        },
                        valueAxis: {
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        title: {
                            visible: false,
                        }

                    }
                },{
                    key : "6",
                    name : "Combined Column & Line",
                    vizType : "timeseries_combination",
                    json : "/column/medium.json",
                    value : ["Revenue", "Cost"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Revenue',
                            value: '{Revenue}'
                        },{
                            name: 'Cost',
                            value: '{Cost}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            }
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "7",
                    name : "Combined Column & Line with Dual Axis",
                    vizType : "dual_timeseries_combination",
                    json : "/column/medium.json",
                    value : ["Revenue", "Cost"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Revenue',
                            value: '{Revenue}'
                        },{
                            name: 'Cost',
                            value: '{Cost}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            }
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        valueAxis2: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "8",
                    name : "Bullet",
                    vizType : "timeseries_bullet",
                    json : "/timeBulletStacked.json",
                    value : ["Cost", "Budget"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Cost',
                            value: '{Cost}'
                        },{
                            name: 'Budget',
                            value: '{Budget}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                            dataPointStyle : {
                                rules : [{
                                    dataContext : {Cost : "*"},
                                    properties : {
                                    	color : "sapUiChartPaletteSequentialHue1Light1"
                                    },
                                    displayName : "Actual",
                                    dataName : {Cost : "Actual"}
                                }]
                            }
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        valueAxis2: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "9",
                    name : "Stacked Column",
                    vizType : "timeseries_stacked_column",
                    json : "/timeBulletStacked.json",
                    value : ["Cost2", "Cost1"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Cost2',
                            value: '{Cost2}'
                        },{
                            name: 'Cost1',
                            value: '{Cost1}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        valueAxis2: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "10",
                    name : "Stacked Column 100%",
                    vizType : "timeseries_100_stacked_column",
                    json : "/timeBulletStacked.json",
                    value : ["Cost2", "Cost1"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Cost2',
                            value: '{Cost2}'
                        },{
                            name: 'Cost1',
                            value: '{Cost1}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        valueAxis2: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "11",
                    name : "Waterfall",
                    vizType : "timeseries_waterfall",
                    json : "/timeWaterFall.json",
                    value : ["Change of Stock"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Change of Stock',
                            value: '{Change of Stock}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false
                            },
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                },{
                    key : "12",
                    name : "Periodic Waterfall",
                    vizType : "timeseries_waterfall",
                    json : "/demands_supplies.json",
                    value : ["Supplies", "Demands"],
                    dataset : {
                        dimensions: [{
                            name: 'Date',
                            value: "{Date}",
                            dataType:'date'
                        }],
                        measures: [{
                            name: 'Demands',
                            value: '{Demands}'
                        },{
                            name: 'Supplies',
                            value: '{Supplies}'
                        }],
                        data: {
                            path: "/milk"
                        }
                    },
                    vizProperties : {
                        plotArea: {
                            window: {
                                start: "firstDataPoint",
                                end: "lastDataPoint"
                            },
                            dataLabel: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_2,
                                visible: false,
                                recapTitle: "End of day",
                                showRecap: true
                            },
                            startValue: 10
                        },
                        valueAxis: {
                            visible: true,
                            label: {
                                formatString:CustomerFormat.FIORI_LABEL_SHORTFORMAT_10
                            },
                            title: {
                                visible: false
                            }
                        },
                        timeAxis: {
                            title: {
                                visible: false
                            },
                            interval : {
                                unit : ''
                            }
                        },
                        legend: {
                            title: {
                                visible: false
                            },
                            label: {
                                text: {
                                    negativeValue: "Demands",
                                    positiveValue: "Supplies"
                                }
                            }
                        },
                        title: {
                            visible: false
                        },
                        interaction: {
                            syncValueAxis: false
                        }
                    }
                }]
            },
            timeLevels : {
                name: "Time Levels",
                defaultSelected : 0,
                values : [{
                    name : "D/M/Y",
                    value : {
                        timeAxis: {
                            levels: ["day", "month", "year"]
                        }
                    }
                },{
                    name : "D/M/Q/Y",
                    value : {
                        timeAxis: {
                            levels: ["day", "month", "quarter", "year"]
                        }
                    }
                }]
            },
            timeFormat : {
                name : "Time Format",
                defaultSelected : 1,
                values : [{
                    name : "01/01/15",
                    yearFormat : CustomerFormat.YFS0,
                    monthFormat : CustomerFormat.MFS2,
                    dayFormat : CustomerFormat.MDFS1
                }, {
                    name : 'Jan 1, 2015',
                    yearFormat : CustomerFormat.YFS1,
                    monthFormat : CustomerFormat.MFS3,
                    dayFormat : CustomerFormat.MDFS2
                }, {
                    name : 'January 1, 2015',
                    yearFormat : CustomerFormat.YFS1,
                    monthFormat : CustomerFormat.MFS4,
                    dayFormat : CustomerFormat.MDFS3
                }]
            },
            dataLabel : {
                name: "Labels Display",
                defaultSelected : 0,
                values : [{
                    name : "Responsive (Default)",
                    row : 2,
                    showFirstLastDataOnly: false,
                    forceToShowFirstLastData: true
                },{
                    name : "First / Last Date Only",
                    row : 1,
                    showFirstLastDataOnly: true,
                    forceToShowFirstLastData: false
                }]
            }
        },

        oVizFrame : null, chartTypeSelect : null, timeFormatRadioGroup : null, dataLabelRadioGroup : null, chart : null,

        onInit : function (evt) {
            this.initCustomFormat();
            // set explored app's demo model on this sample
            var oModel = new JSONModel(this.settingsModel);
            oModel.setDefaultBindingMode(sap.ui.model.BindingMode.OneWay);
            this.getView().setModel(oModel);

            var oVizFrame = this.oVizFrame = this.getView().byId("idVizFrame");
            var bindValue = this.settingsModel.timeFormat.values[1];
            var rowValue = this.settingsModel.dataLabel.values[0];
            this.setFormat(bindValue);
            oVizFrame.setVizProperties(jQuery.extend(true, {}, this.settingsModel.chartType.values[3].vizProperties, this.getVizProperties(bindValue,rowValue)));
            var dataModel = new JSONModel("chartTypes/timeAxis.json");
            oVizFrame.setModel(dataModel);

            var oPopOver = this.getView().byId("idPopOver");
            oPopOver.connect(oVizFrame.getVizUid());
            oPopOver.setFormatString({"Cost":CustomerFormat.FIORI_LABEL_FORMAT_2,"Revenue":CustomerFormat.FIORI_LABEL_FORMAT_2});

            InitPageUtil.initPageSettings(this.getView());
        },
        onAfterRendering : function(){
            this.chartTypeSelect = this.getView().byId('chartTypeSelect');

            var timeLevelsRadioGroup = this.getView().byId('timeLevelsRadioGroup');
            timeLevelsRadioGroup.setSelectedIndex(this.settingsModel.timeLevels.defaultSelected);

            this.timeFormatRadioGroup = this.getView().byId('timeFormatRadioGroup');
            this.timeFormatRadioGroup.setSelectedIndex(this.settingsModel.timeFormat.defaultSelected);

            this.dataLabelRadioGroup = this.getView().byId('dataLabelRadioGroup');
            this.dataLabelRadioGroup.setSelectedIndex(this.settingsModel.dataLabel.defaultSelected);
        },
        onChartTypeChanged : function(oEvent){
            if(this.oVizFrame){
                var selectedKey = this.chart = parseInt(oEvent.getSource().getSelectedKey());
                var bindValue = this.settingsModel.chartType.values[selectedKey];
                this.oVizFrame.destroyDataset();
                this.oVizFrame.destroyFeeds();
                this.oVizFrame.setVizType(bindValue.vizType);
                var dataModel = new JSONModel(this.dataPath + bindValue.json);
                this.oVizFrame.setModel(dataModel);
                var oDataset = new FlattenedDataset(bindValue.dataset);
                this.oVizFrame.setDataset(oDataset);
                var props = bindValue.vizProperties;
                if (selectedKey !== 8 && props.plotArea) {
                	props.plotArea.dataPointStyle = null;
                }
                this.oVizFrame.setVizProperties(props);
                var feedValueAxis, feedValueAxis2, feedActualValues, feedTargetValues;
                if (selectedKey === 7) {
                    feedValueAxis = new FeedItem({
                        'uid': "valueAxis",
                        'type': "Measure",
                        'values': [bindValue.value[0]]
                    });
                    feedValueAxis2 = new FeedItem({
                        'uid': "valueAxis2",
                        'type': "Measure",
                        'values': [bindValue.value[1]]
                    });
                } else if (selectedKey === 8) {
                    feedActualValues = new FeedItem({
                        'uid': "actualValues",
                        'type': "Measure",
                        'values': [bindValue.value[0]]
                    });
                    feedTargetValues = new FeedItem({
                        'uid': "targetValues",
                        'type': "Measure",
                        'values': [bindValue.value[1]]
                    });
                } else {
                    feedValueAxis = new FeedItem({
                        'uid': "valueAxis",
                        'type': "Measure",
                        'values': bindValue.value
                    });
                }

                var feedTimeAxis = new FeedItem({
                    'uid': "timeAxis",
                    'type': "Dimension",
                    'values': ["Date"]
                }),
                feedBubbleWidth = new FeedItem({
                    "uid": "bubbleWidth",
                    "type": "Measure",
                    "values": ["Revenue"]
                });
                switch(selectedKey){
                    case 0:
                        this.oVizFrame.addFeed(feedValueAxis);
                        this.oVizFrame.addFeed(feedTimeAxis);
                        this.oVizFrame.addFeed(feedBubbleWidth);
                        break;
                    case 7:
                        this.oVizFrame.addFeed(feedValueAxis);
                        this.oVizFrame.addFeed(feedValueAxis2);
                        this.oVizFrame.addFeed(feedTimeAxis);
                        break;
                    case 8:
                        this.oVizFrame.addFeed(feedActualValues);
                        this.oVizFrame.addFeed(feedTargetValues);
                        this.oVizFrame.addFeed(feedTimeAxis);
                    default:
                        this.oVizFrame.addFeed(feedValueAxis);
                        this.oVizFrame.addFeed(feedTimeAxis);
                        break;
                }
            }
        },
        onTimeLevelsSelected : function(oEvent){
            var timeLevelsRadio = oEvent.getSource();
            if(this.oVizFrame && timeLevelsRadio.getSelected()){
                var bindValue = timeLevelsRadio.getBindingContext().getObject();
                this.oVizFrame.setVizProperties(bindValue.value);
            }
        },
        onTimeFormatSelected : function(oEvent){
            if (!oEvent.getParameters().selected) {
                return;
            }
            var timeFormatRadio = oEvent.getSource();
            if(this.oVizFrame && timeFormatRadio.getSelected()){
                var bindValue = timeFormatRadio.getBindingContext().getObject();
                var rowValue = this.settingsModel.dataLabel.values[this.dataLabelRadioGroup.getSelectedIndex()];
                this.setFormat(bindValue);
                this.oVizFrame.setVizProperties(this.getVizProperties(bindValue,rowValue));
            }
        },
        onDataLabelSelected : function(oEvent){
            if (!oEvent.getParameters().selected) {
                return;
            }
            var dataLabelRadio = oEvent.getSource();
            if(this.oVizFrame && dataLabelRadio.getSelected()){
                var bindValue = dataLabelRadio.getBindingContext().getObject();
                var rowValue = this.settingsModel.timeFormat.values[this.timeFormatRadioGroup.getSelectedIndex()];
                this.setFormat(rowValue);
                this.oVizFrame.setVizProperties(this.getVizProperties(rowValue,bindValue));
            }
        },
        initCustomFormat : function(){
            CustomerFormat.registerCustomFormat();
        },
        getVizProperties : function(timeFormat,rowValue){
            return {
                timeAxis: {
                    levelConfig: {
                        year:{
                            row: rowValue.row,
                            formatString: timeFormat.yearFormat
                        },
                        quarter:{
                            row: rowValue.row
                        },
                        month:{
                            row: 1,
                            formatString: timeFormat.monthFormat
                        },
                        day: {
                            row: 1,
                            formatString: timeFormat.dayFormat
                        }
                    },
                    label:{
                       showFirstLastDataOnly: rowValue.showFirstLastDataOnly,
                       forceToShowFirstLastData: rowValue.forceToShowFirstLastData
                    }
                }
            }
        },
        setFormat : function(timeFormat){
            if(timeFormat.name == "01/01/15"){
                CustomerFormat.chartFormatter.registerCustomFormatter("YearMonthDay", function(value) {
                    var ymdf = sap.ui.core.format.DateFormat.getDateTimeInstance({pattern: "MM/dd/yy"})
                    return ymdf.format(value);
                })
            }else{
                CustomerFormat.chartFormatter.registerCustomFormatter("YearMonthDay", null);
            }
        }
    });

    return Controller;

});
