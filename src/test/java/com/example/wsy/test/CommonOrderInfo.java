/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @since 0.0.1
 */

package com.example.wsy.test;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhaoyunji
 * @since 1.0.0
 */
@Data
public class CommonOrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String waybillNo;
    /**
     * 箱号
     */
    private String boxNo;
    /**
     * 购买人ID
     */
    private String buyerId;
    /**
     * 购买人名称
     */
    private String buyerName;
    /**
     * 购买人电话
     */
    private String buyerPhone;
    /**
     * 代收货款
     */
    private Double coValue;
    /**
     * 货物类型编码
     */
    private String cargoTypeCode;
    /**
     * 货物类型名称
     */
    private String cargoTypeName;
    /**
     * 运输金额
     */
    private Double carriage;
    /**
     * 渠道号
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道转单号
     */
    private String channelHawbcode;
    /**
     * 商品标签
     */
    private String commodityTag;
    /**
     * 收件人地址
     */
    private String consigneeAddress;
    /**
     * 收件人区县
     */
    private String consigneeArea;
    /**
     * 收件人区县编码
     */
    private String consigneeAreaCode;
    /**
     * 证件类型代码
     */
    private String consigneeCertificateCode;
    /**
     * 证件名称
     */
    private String consigneeCertificateName;
    /**
     * 收件人证件号码
     */
    private String consigneeCertificateNumber;
    /**
     * 收件人城市
     */
    private String consigneeCity;
    /**
     * 收件人城市编码
     */
    private String consigneeCityCode;
    /**
     * 收件人公司
     */
    private String consigneeCompany;
    /**
     * 收件人ID
     */
    private String consigneeId;
    /**
     * 收件人手机
     */
    private String consigneeMobile;
    /**
     * 收件人姓名
     */
    private String consigneeName;
    /**
     * 收件人电话
     */
    private String consigneePhone;
    /**
     * 收件人省/州
     */
    private String consigneeProvince;
    /**
     * 收件人省编码
     */
    private String consigneeProvinceCode;

    private String consigneePostCode;
    /**
     * 订单创建日期
     */
    private Timestamp createTime;
    /**
     * 订单创建人工号
     */
    private String creatorId;
    /**
     * 客户code
     */
    private String customerCode;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 关务申报：1正式申报，0非正式申报
     */
    private String customsType;
    /**
     * 包裹申报类型名称
     */
    private String declareName;
    /**
     * 包裹申报类型:1:包裹申报类型；2:商品样货；3:文件；4：其它
     */
    private Integer declareType;
    /**
     * 1 客户自送 2 上门揽收
     */
    private Integer deliveryType;
    /**
     * 目的地CODE
     */
    private String destinationCode;
    /**
     * 目的地名称
     */
    private String destinationName;
    /**
     * 拦截状态(0:未拦截 1:已拦截 )
     */
    private Integer holdSign;
    /**
     * 订单ID
     */
    private String id;
    /**
     * 保险费(投保金额)
     */
    private Double insurancePremium;
    /**
     * 是否需要分单:0:不需要分单;1:需要分单
     */
    private Integer isAssign;
    /**
     * 是否为补录的单：0:否;1:是
     */
    private Integer isBackTracking;
    /**
     * 打印次数
     */
    private Integer isPrint;
    /**
     * 是否需要接单:0:否;1:是
     */
    private Integer isTakeOrder;
    /**
     * 是否上传证件(0代表未上传)
     */
    private Integer isUploadCertificate;
    /**
     * 是否上传身份证（0：未上传 1：已上传）
     */
    private String isUploadIdCard;
    /**
     * 金刚所属网点代码
     */
    private String jgStationCode;
    /**
     * 金刚所属网点名称
     */
    private String jgStationName;
    /**
     * 最新轨迹
     */
    private String lastTrack;
    /**
     * 主单号
     */
    private String mainOrderId;
    /**
     * 报单类型：1正式报单，2简易报单，0不报单
     */
    private Integer needOrderFlag;
    /**
     * 机构代码
     */
    private String ogShortCode;
    /**
     * 机构名称
     */
    private String ogShortName;
    /**
     * 客户单号(订单号)
     */
    private String orderId;
    /**
     * S一票一件，MM一票多件母单，MS一票多件子单
     */
    private String parcelFlag;
    /**
     * 自提点ID
     */
    private String pickSiteId;
    /**
     * 自提点名称
     */
    private String pickSiteName;
    /**
     * 货物件数
     */
    private Integer piece;
    /**
     * 平台id
     */
    private String platformId;
    /**
     * 催件次数
     */
    private Integer pushCount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 保留字段1
     */
    private String reserve1;
    /**
     * 保留字段2
     */
    private String reserve2;
    /**
     * 服务商单号(运单号)
     */
    private String serverHawbcode;
    /**
     * 发件人地址
     */
    private String shipperAddress;
    /**
     * 发件人区县
     */
    private String shipperArea;
    /**
     * 发件人区县code
     */
    private String shipperAreaCode;
    /**
     * 发件人城市
     */
    private String shipperCity;
    /**
     * 发件人城市code
     */
    private String shipperCityCode;
    /**
     * 发件人公司
     */
    private String shipperCompany;
    /**
     * 发件人国家名称
     */
    private String shipperCountry;
    /**
     * 发件人国家code
     */
    private String shipperCountryCode;
    /**
     * 发件人ID
     */
    private String shipperId;
    /**
     * 发件人手机
     */
    private String shipperMobile;
    /**
     * 发件人姓名
     */
    private String shipperName;
    /**
     * 发件人电话
     */
    private String shipperPhone;
    /**
     * 发件人省份/州
     */
    private String shipperProvince;
    /**
     * 发件人省份/州code
     */
    private String shipperProvinceCode;
    /**
     * 订单来源
     */
    private Integer source;
    /**
     * 订单来源编码
     */
    private String sourceCode;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 订单状态国际化code
     */
    private String statusCode;
    /**
     * 状态更新机构(操作机构)
     */
    private String statusOgName;
    /**
     * 状态更新机构代码(操作机构)
     */
    private String statusOgShortCode;
    /**
     * 第三方单号(溯源码)
     */
    private String thirdBillNo;
    /**
     * 单号类型：0:无;1:溯源码
     */
    private Integer thirdBillType;
    /**
     * 运输方式CODE(产品)
     */
    private String transportModeCode;
    /**
     * 运输方式名称(产品)
     */
    private String transportModeName;
    /**
     * 修改人工号
     */
    private String updateId;
    /**
     * 订单修改日期
     */
    private Timestamp updateTime;
    /**
     * 货物重量
     */
    private Double weight;
    /**
     * 圆通三段码
     */
    private String ytoDistributeCode;
    /**
     * 报关信息集合
     */
//    private List<OrderInvoiceInfo> orderInvoices;
    /**
     * 报关类型 AA ,BB, C-
     */
    private String modeCode;
    /**
     * 加盟商代码
     */
    private String serviceCode;
    /**
     * 加盟商名称
     */
    private String serviceName;
    /**
     * 订单状态
     */
    private String orderStatus;

    private int logPrintType;
    /**
     * 订单重量
     */
    private String orderWeight;

    private String orderPieces;
}
