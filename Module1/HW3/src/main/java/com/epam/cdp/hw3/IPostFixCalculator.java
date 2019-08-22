package com.epam.cdp.hw3;

import java.math.BigDecimal;
import java.util.List;

public interface IPostFixCalculator {

    BigDecimal result(List<String> postfix);
}
