package com.zhaochuninhefei.webpmjava.db.po;

import java.math.BigDecimal;

@lombok.Getter
@lombok.Setter
@lombok.ToString
public class AmountByCtmLevel {
    private int ctmLevel;
    private BigDecimal amount;
}
