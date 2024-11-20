package lk.ijse.gdse72.shaanfashion.model;

public class OrderDetailsModel {

    private final ItemModel itemModel = new ItemModel();

    /*public boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException {
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
            boolean isOrderDetailsSaved = saveOrderDetail(orderDetailsDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = itemModel.reduceQty(orderDetailsDTO);
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
    }*/
}
