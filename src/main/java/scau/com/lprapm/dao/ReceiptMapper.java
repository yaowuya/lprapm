package scau.com.lprapm.dao;

import scau.com.lprapm.entity.Receipt;

public interface ReceiptMapper {
    int deleteByPrimaryKey(Integer receiptId);

    int insert(Receipt record);

    int insertSelective(Receipt record);

    Receipt selectByPrimaryKey(Integer receiptId);

    int updateByPrimaryKeySelective(Receipt record);

    int updateByPrimaryKey(Receipt record);

    int insertReceipt(Receipt receipt);
}