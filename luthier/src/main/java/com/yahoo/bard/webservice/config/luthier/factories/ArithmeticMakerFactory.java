package com.yahoo.bard.webservice.config.luthier.factories;

import com.yahoo.bard.webservice.config.luthier.ConceptType;
import com.yahoo.bard.webservice.config.luthier.Factory;
import com.yahoo.bard.webservice.config.luthier.LuthierIndustrialPark;
import com.yahoo.bard.webservice.config.luthier.LuthierValidationUtils;
import com.yahoo.bard.webservice.data.config.metric.makers.ArithmeticMaker;
import com.yahoo.bard.webservice.data.config.metric.makers.MetricMaker;
import com.yahoo.bard.webservice.druid.model.postaggregation.ArithmeticPostAggregation;
import com.yahoo.bard.webservice.druid.model.postaggregation.ArithmeticPostAggregation.ArithmeticPostAggregationFunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArithmeticMakerFactory implements Factory<MetricMaker> {

    public static final String OPERATION = "operation";

    @Override
    public MetricMaker build(String name, ObjectNode configTable, LuthierIndustrialPark resourceFactories) {
        LuthierValidationUtils.validateField(configTable.get(OPERATION), ConceptType.METRIC_MAKER, name, OPERATION);
        String opName = configTable.get(OPERATION).textValue();
        ArithmeticPostAggregationFunction operation = ArithmeticPostAggregationFunction.valueOf(opName);
        return new ArithmeticMaker(resourceFactories.getMetricDictionary(), operation);
    }
}
