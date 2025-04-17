



User (/api/v1/user/)
    create - create user profile (/create) : usercreaterequest : POST
    me - view user profile (/) : username : GET
    credit-limit : view credit limit(/credit-limit) :username : GET
    available-limit : view available limit (/available-limit) : username : GET
    orders : view my orders (/orders) : username : GET
    orderStatus: view order status wise (/order-status) : username : GET
    paymentStatus: view payment by status (/payment-status) : username : GET
    paymentType : view payment by type (/payment-type) : username : GET

UserCreateRequest
    firstname
    lastname
    phone
    email

Product(/api/v1/product)
    add : add product (/add) : add request : POST
    getdetails : get product details for particular merchant (/details) : request : GET
    viewCost : view specific product cost for particular merchant (/cost) : request : GET
    viewQtyLeft : view product qty left for particular merchant (/qty) : request : GET
    update: update a product for particular merchant (/modify) : request : PUT
    delete : delete a specific product for particular merchant (/delete) : request : DELETE
    compare: compare minimun price for a product on merchant (/compare) : request (only productType) : GET
    viewProductType: view the product provided by all the merchants (/view-products) : request(onlu productType) : GET

request:
    productID
    merchantID
    MerchantType
    ProductType
    
Merchant(/api/v1/merchant)
    add : add merchant (/add) : add request : POST
    getDetails : get merchant details  (/details) : request : GET
    viewProductsForMerchant : view all products for the merchant  (/cost) : request : GET
    update: update a merchant for  (/modify) : request : PUT
    delete : delete a specific merchant  (/delete) : request : DELETE
    getAllMerchant : get All merchant (/) : GET

request:
    merchantId
    

Order(/api/v1/order)
    place: place order from cart (/place) : request : POST
    getOrderDetail : get order detail : request : GET
    getOrderStatusWise : get order status wise (/status) : request : GET
    getMerchantOrder : get orders wrt merchant (/merchant) : merchantId : GET
    cancelOrder : cancel an order (/cancel) : request : POST

request
    orderId


Transaction(/ap1/v1/transaction)
    paynow: make payment now (/paynow) : request(order_id) : POST
    bnpl: make bnpl payment (/bnpl) : request(order_id) : POST
    

BNPL(/api/v1/bnpl)
    repayment : repayment Global for a particular user (/repayment) : request : POST
    repaymentOrderId : repay particular order for a particular user (/repayment/order) : request : POST
    bnplOrderView : view a particular bnpl Order for particular user (/user/order) : request : GET
    bnplAllOrderView : view all BNPL order : request (/orders): GET
    bnplOrderStatus : view order wrt status (/order-status) : request : GET


BNPLControllerRequest
    username
    amount
    orderID


