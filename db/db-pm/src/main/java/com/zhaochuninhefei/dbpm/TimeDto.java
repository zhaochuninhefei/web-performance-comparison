package com.zhaochuninhefei.dbpm;

import java.util.List;

/**
 * @author zhaochun
 */
@SuppressWarnings("unused")
public class TimeDto {
    private static final String HEADER = "prepareData 耗时(毫秒),truncateTables 耗时(毫秒),insertOrder 耗时(毫秒),insertCustom 耗时(毫秒),insertProduct 耗时(毫秒),insertWarehouse 耗时(毫秒),selectOrders conn 耗时(毫秒),selectOrders executeQuery 耗时(毫秒),selectOrders ResultSet遍历 耗时(毫秒),selectOrders 总耗时(毫秒),selectOrders 件数,selectCustoms conn 耗时(毫秒),selectCustoms executeQuery 耗时(毫秒),selectCustoms ResultSet遍历 耗时(毫秒),selectCustoms 总耗时(毫秒),selectCustoms 件数,selectProducts conn 耗时(毫秒),selectProducts executeQuery 耗时(毫秒),selectProducts ResultSet遍历 耗时(毫秒),selectProducts 总耗时(毫秒),selectProducts 件数,selectWarehouses conn 耗时(毫秒),selectWarehouses executeQuery 耗时(毫秒),selectWarehouses ResultSet遍历 耗时(毫秒),selectWarehouses 总耗时(毫秒),selectWarehouses 件数,selectOrderJoinCustom conn 耗时(毫秒),selectOrderJoinCustom executeQuery 耗时(毫秒),selectOrderJoinCustom ResultSet遍历 耗时(毫秒),selectOrderJoinCustom 总耗时(毫秒),selectOrderJoinCustom 件数,selectOrderJoinProduct conn 耗时(毫秒),selectOrderJoinProduct executeQuery 耗时(毫秒),selectOrderJoinProduct ResultSet遍历 耗时(毫秒),selectOrderJoinProduct 总耗时(毫秒),selectOrderJoinProduct 件数,selectOrderJoinWarehouse conn 耗时(毫秒),selectOrderJoinWarehouse executeQuery 耗时(毫秒),selectOrderJoinWarehouse ResultSet遍历 耗时(毫秒),selectOrderJoinWarehouse 总耗时(毫秒),selectOrderJoinWarehouse 件数,selectOrderWithOrderBy conn 耗时(毫秒),selectOrderWithOrderBy executeQuery 耗时(毫秒),selectOrderWithOrderBy ResultSet遍历 耗时(毫秒),selectOrderWithOrderBy 总耗时(毫秒),selectOrderWithOrderBy 件数";

    // prepareData 耗时(毫秒)
    private long prepareDataTime;

    // truncateTables 耗时(毫秒)
    private long truncateTime;

    // insertOrder 耗时(毫秒)
    private long insertOrdTime;
    // insertCustom 耗时(毫秒)
    private long insertCstTime;
    // insertProduct 耗时(毫秒)
    private long insertPrdTime;
    // insertWarehouse 耗时(毫秒)
    private long insertWhsTime;

    // selectOrders conn 耗时(毫秒)
    private long selectOrdConnTime;
    // selectOrders executeQuery 耗时(毫秒)
    private long selectOrdExecTime;
    // selectOrders ResultSet遍历 耗时(毫秒)
    private long selectOrdLoopTime;
    // selectOrders 总耗时(毫秒)
    private long selectOrdAllTime;
    // selectOrders 件数
    private long ordSize;

    // selectCustoms conn 耗时(毫秒)
    private long selectCstConnTime;
    // selectCustoms executeQuery 耗时(毫秒)
    private long selectCstExecTime;
    // selectCustoms ResultSet遍历 耗时(毫秒)
    private long selectCstLoopTime;
    // selectCustoms 总耗时(毫秒)
    private long selectCstAllTime;
    // selectCustoms 件数
    private long cstSize;

    // selectProducts conn 耗时(毫秒)
    private long selectPrdConnTime;
    // selectProducts executeQuery 耗时(毫秒)
    private long selectPrdExecTime;
    // selectProducts ResultSet遍历 耗时(毫秒)
    private long selectPrdLoopTime;
    // selectProducts 总耗时(毫秒)
    private long selectPrdAllTime;
    // selectProducts 件数
    private long prdSize;

    // selectWarehouses conn 耗时(毫秒)
    private long selectWhsConnTime;
    // selectWarehouses executeQuery 耗时(毫秒)
    private long selectWhsExecTime;
    // selectWarehouses ResultSet遍历 耗时(毫秒)
    private long selectWhsLoopTime;
    // selectWarehouses 总耗时(毫秒)
    private long selectWhsAllTime;
    // selectWarehouses 件数
    private long whsSize;

    // selectOrderJoinCustom conn 耗时(毫秒)
    private long selectOcConnTime;
    // selectOrderJoinCustom executeQuery 耗时(毫秒)
    private long selectOcExecTime;
    // selectOrderJoinCustom ResultSet遍历 耗时(毫秒)
    private long selectOcLoopTime;
    // selectOrderJoinCustom 总耗时(毫秒)
    private long selectOcAllTime;
    // selectOrderJoinCustom 件数
    private long ocSize;

    // selectOrderJoinProduct conn 耗时(毫秒)
    private long selectOpConnTime;
    // selectOrderJoinProduct executeQuery 耗时(毫秒)
    private long selectOpExecTime;
    // selectOrderJoinProduct ResultSet遍历 耗时(毫秒)
    private long selectOpLoopTime;
    // selectOrderJoinProduct 总耗时(毫秒)
    private long selectOpAllTime;
    // selectOrderJoinProduct 件数
    private long opSize;

    // selectOrderJoinWarehouse conn 耗时(毫秒)
    private long selectOwConnTime;
    // selectOrderJoinWarehouse executeQuery 耗时(毫秒)
    private long selectOwExecTime;
    // selectOrderJoinWarehouse ResultSet遍历 耗时(毫秒)
    private long selectOwLoopTime;
    // selectOrderJoinWarehouse 总耗时(毫秒)
    private long selectOwAllTime;
    // selectOrderJoinWarehouse 件数
    private long owSize;

    // selectOrderWithOrderBy conn 耗时(毫秒)
    private long selectOobConnTime;
    // selectOrderWithOrderBy executeQuery 耗时(毫秒)
    private long selectOobExecTime;
    // selectOrderWithOrderBy ResultSet遍历 耗时(毫秒)
    private long selectOobLoopTime;
    // selectOrderWithOrderBy 总耗时(毫秒)
    private long selectOobAllTime;
    // selectOrderWithOrderBy 件数
    private long oobSize;

    public static String createInfo(List<TimeDto> timeDtos) {
        // 遍历 timeDtos 调用 createStaticsInfo 生成统计信息，并在前面拼接头行 HEADER
        var info = new StringBuilder(HEADER);
        for (TimeDto timeDto : timeDtos) {
            info.append("\n").append(timeDto.createStaticsInfo(false));
        }
        return info.toString();
    }

    public String createStaticsInfo(boolean withHeader) {
        var bodyLine = String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                prepareDataTime,
                truncateTime,
                insertOrdTime,
                insertCstTime,
                insertPrdTime,
                insertWhsTime,
                selectOrdConnTime,
                selectOrdExecTime,
                selectOrdLoopTime,
                selectOrdAllTime,
                ordSize,
                selectCstConnTime,
                selectCstExecTime,
                selectCstLoopTime,
                selectCstAllTime,
                cstSize,
                selectPrdConnTime,
                selectPrdExecTime,
                selectPrdLoopTime,
                selectPrdAllTime,
                prdSize,
                selectWhsConnTime,
                selectWhsExecTime,
                selectWhsLoopTime,
                selectWhsAllTime,
                whsSize,
                selectOcConnTime,
                selectOcExecTime,
                selectOcLoopTime,
                selectOcAllTime,
                ocSize,
                selectOpConnTime,
                selectOpExecTime,
                selectOpLoopTime,
                selectOpAllTime,
                opSize,
                selectOwConnTime,
                selectOwExecTime,
                selectOwLoopTime,
                selectOwAllTime,
                owSize,
                selectOobConnTime,
                selectOobExecTime,
                selectOobLoopTime,
                selectOobAllTime,
                oobSize
                );
        if (withHeader) {
            return HEADER + "\n" + bodyLine;
        } else {
            return bodyLine;
        }
    }

    public long getPrepareDataTime() {
        return prepareDataTime;
    }

    public void setPrepareDataTime(long prepareDataTime) {
        this.prepareDataTime = prepareDataTime;
    }

    public long getTruncateTime() {
        return truncateTime;
    }

    public void setTruncateTime(long truncateTime) {
        this.truncateTime = truncateTime;
    }

    public long getInsertOrdTime() {
        return insertOrdTime;
    }

    public void setInsertOrdTime(long insertOrdTime) {
        this.insertOrdTime = insertOrdTime;
    }

    public long getInsertCstTime() {
        return insertCstTime;
    }

    public void setInsertCstTime(long insertCstTime) {
        this.insertCstTime = insertCstTime;
    }

    public long getInsertPrdTime() {
        return insertPrdTime;
    }

    public void setInsertPrdTime(long insertPrdTime) {
        this.insertPrdTime = insertPrdTime;
    }

    public long getInsertWhsTime() {
        return insertWhsTime;
    }

    public void setInsertWhsTime(long insertWhsTime) {
        this.insertWhsTime = insertWhsTime;
    }

    public long getSelectOrdConnTime() {
        return selectOrdConnTime;
    }

    public void setSelectOrdConnTime(long selectOrdConnTime) {
        this.selectOrdConnTime = selectOrdConnTime;
    }

    public long getSelectOrdExecTime() {
        return selectOrdExecTime;
    }

    public void setSelectOrdExecTime(long selectOrdExecTime) {
        this.selectOrdExecTime = selectOrdExecTime;
    }

    public long getSelectOrdLoopTime() {
        return selectOrdLoopTime;
    }

    public void setSelectOrdLoopTime(long selectOrdLoopTime) {
        this.selectOrdLoopTime = selectOrdLoopTime;
    }

    public long getSelectOrdAllTime() {
        return selectOrdAllTime;
    }

    public void setSelectOrdAllTime(long selectOrdAllTime) {
        this.selectOrdAllTime = selectOrdAllTime;
    }

    public long getOrdSize() {
        return ordSize;
    }

    public void setOrdSize(long ordSize) {
        this.ordSize = ordSize;
    }

    public long getSelectCstConnTime() {
        return selectCstConnTime;
    }

    public void setSelectCstConnTime(long selectCstConnTime) {
        this.selectCstConnTime = selectCstConnTime;
    }

    public long getSelectCstExecTime() {
        return selectCstExecTime;
    }

    public void setSelectCstExecTime(long selectCstExecTime) {
        this.selectCstExecTime = selectCstExecTime;
    }

    public long getSelectCstLoopTime() {
        return selectCstLoopTime;
    }

    public void setSelectCstLoopTime(long selectCstLoopTime) {
        this.selectCstLoopTime = selectCstLoopTime;
    }

    public long getSelectCstAllTime() {
        return selectCstAllTime;
    }

    public void setSelectCstAllTime(long selectCstAllTime) {
        this.selectCstAllTime = selectCstAllTime;
    }

    public long getCstSize() {
        return cstSize;
    }

    public void setCstSize(long cstSize) {
        this.cstSize = cstSize;
    }

    public long getSelectPrdConnTime() {
        return selectPrdConnTime;
    }

    public void setSelectPrdConnTime(long selectPrdConnTime) {
        this.selectPrdConnTime = selectPrdConnTime;
    }

    public long getSelectPrdExecTime() {
        return selectPrdExecTime;
    }

    public void setSelectPrdExecTime(long selectPrdExecTime) {
        this.selectPrdExecTime = selectPrdExecTime;
    }

    public long getSelectPrdLoopTime() {
        return selectPrdLoopTime;
    }

    public void setSelectPrdLoopTime(long selectPrdLoopTime) {
        this.selectPrdLoopTime = selectPrdLoopTime;
    }

    public long getSelectPrdAllTime() {
        return selectPrdAllTime;
    }

    public void setSelectPrdAllTime(long selectPrdAllTime) {
        this.selectPrdAllTime = selectPrdAllTime;
    }

    public long getPrdSize() {
        return prdSize;
    }

    public void setPrdSize(long prdSize) {
        this.prdSize = prdSize;
    }

    public long getSelectWhsConnTime() {
        return selectWhsConnTime;
    }

    public void setSelectWhsConnTime(long selectWhsConnTime) {
        this.selectWhsConnTime = selectWhsConnTime;
    }

    public long getSelectWhsExecTime() {
        return selectWhsExecTime;
    }

    public void setSelectWhsExecTime(long selectWhsExecTime) {
        this.selectWhsExecTime = selectWhsExecTime;
    }

    public long getSelectWhsLoopTime() {
        return selectWhsLoopTime;
    }

    public void setSelectWhsLoopTime(long selectWhsLoopTime) {
        this.selectWhsLoopTime = selectWhsLoopTime;
    }

    public long getSelectWhsAllTime() {
        return selectWhsAllTime;
    }

    public void setSelectWhsAllTime(long selectWhsAllTime) {
        this.selectWhsAllTime = selectWhsAllTime;
    }

    public long getWhsSize() {
        return whsSize;
    }

    public void setWhsSize(long whsSize) {
        this.whsSize = whsSize;
    }

    public long getSelectOcConnTime() {
        return selectOcConnTime;
    }

    public void setSelectOcConnTime(long selectOcConnTime) {
        this.selectOcConnTime = selectOcConnTime;
    }

    public long getSelectOcExecTime() {
        return selectOcExecTime;
    }

    public void setSelectOcExecTime(long selectOcExecTime) {
        this.selectOcExecTime = selectOcExecTime;
    }

    public long getSelectOcLoopTime() {
        return selectOcLoopTime;
    }

    public void setSelectOcLoopTime(long selectOcLoopTime) {
        this.selectOcLoopTime = selectOcLoopTime;
    }

    public long getSelectOcAllTime() {
        return selectOcAllTime;
    }

    public void setSelectOcAllTime(long selectOcAllTime) {
        this.selectOcAllTime = selectOcAllTime;
    }

    public long getOcSize() {
        return ocSize;
    }

    public void setOcSize(long ocSize) {
        this.ocSize = ocSize;
    }

    public long getSelectOpConnTime() {
        return selectOpConnTime;
    }

    public void setSelectOpConnTime(long selectOpConnTime) {
        this.selectOpConnTime = selectOpConnTime;
    }

    public long getSelectOpExecTime() {
        return selectOpExecTime;
    }

    public void setSelectOpExecTime(long selectOpExecTime) {
        this.selectOpExecTime = selectOpExecTime;
    }

    public long getSelectOpLoopTime() {
        return selectOpLoopTime;
    }

    public void setSelectOpLoopTime(long selectOpLoopTime) {
        this.selectOpLoopTime = selectOpLoopTime;
    }

    public long getSelectOpAllTime() {
        return selectOpAllTime;
    }

    public void setSelectOpAllTime(long selectOpAllTime) {
        this.selectOpAllTime = selectOpAllTime;
    }

    public long getOpSize() {
        return opSize;
    }

    public void setOpSize(long opSize) {
        this.opSize = opSize;
    }

    public long getSelectOwConnTime() {
        return selectOwConnTime;
    }

    public void setSelectOwConnTime(long selectOwConnTime) {
        this.selectOwConnTime = selectOwConnTime;
    }

    public long getSelectOwExecTime() {
        return selectOwExecTime;
    }

    public void setSelectOwExecTime(long selectOwExecTime) {
        this.selectOwExecTime = selectOwExecTime;
    }

    public long getSelectOwLoopTime() {
        return selectOwLoopTime;
    }

    public void setSelectOwLoopTime(long selectOwLoopTime) {
        this.selectOwLoopTime = selectOwLoopTime;
    }

    public long getSelectOwAllTime() {
        return selectOwAllTime;
    }

    public void setSelectOwAllTime(long selectOwAllTime) {
        this.selectOwAllTime = selectOwAllTime;
    }

    public long getOwSize() {
        return owSize;
    }

    public void setOwSize(long owSize) {
        this.owSize = owSize;
    }

    public long getSelectOobConnTime() {
        return selectOobConnTime;
    }

    public void setSelectOobConnTime(long selectOobConnTime) {
        this.selectOobConnTime = selectOobConnTime;
    }

    public long getSelectOobExecTime() {
        return selectOobExecTime;
    }

    public void setSelectOobExecTime(long selectOobExecTime) {
        this.selectOobExecTime = selectOobExecTime;
    }

    public long getSelectOobLoopTime() {
        return selectOobLoopTime;
    }

    public void setSelectOobLoopTime(long selectOobLoopTime) {
        this.selectOobLoopTime = selectOobLoopTime;
    }

    public long getSelectOobAllTime() {
        return selectOobAllTime;
    }

    public void setSelectOobAllTime(long selectOobAllTime) {
        this.selectOobAllTime = selectOobAllTime;
    }

    public long getOobSize() {
        return oobSize;
    }

    public void setOobSize(long oobSize) {
        this.oobSize = oobSize;
    }
}
