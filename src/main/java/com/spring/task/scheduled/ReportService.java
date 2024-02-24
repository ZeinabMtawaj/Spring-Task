package com.spring.task.scheduled;

import com.spring.task.feature.purchase.Purchase;
import com.spring.task.feature.refund.Refund;
import com.spring.task.repository.PurchaseRepository;
import com.spring.task.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final PurchaseRepository purchaseRepository;
    private final RefundRepository refundRepository;

    public String generateReportForDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Purchase> purchases = purchaseRepository.findByCreatedAtBetween(startOfDay, endOfDay);
//        List<Refund> refunds = refundRepository.findByCreatedAtBetween(startOfDay, endOfDay);
        List<Object[]> refundData = refundRepository.findRefundsGroupedByPurchaseWithDetails(startOfDay, endOfDay);


        StringBuilder reportHtml = new StringBuilder();

        reportHtml.append("<body style='font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; color: #333; position: relative;'>")
                .append("<div style='position: absolute; top: 0; left: 0;'>")
                .append("<img src='https://cdn.pixabay.com/animation/2023/03/05/21/13/21-13-51-872_512.gif' alt='Butterfly' style='width: 60px; height: 60px;'>")
                .append("</div>")
                .append("<h1 style='color: #333; text-align: center;'>Daily Report for ").append(date).append("</h1>");


        String tableStyle = "width: 80%; border-collapse: separate; margin: 20px auto; border-spacing: 0; box-shadow: 0 8px 16px rgba(0,0,0,1); border-radius: 10px; overflow: hidden;";
        String thStyle = "background-color: #6c4481; color: white; padding: 12px 8px; text-align: center;";
        String tdStyle = "border: 1px solid #ddd; padding: 8px; background-color: #ffffff;";


        // Purchases table
        reportHtml.append("<h2 style='text-align: center';>Purchases:</h2>")
                .append("<table style='").append(tableStyle).append("'><tr style='").append(thStyle).append("'>")
                .append("<th style='").append(thStyle).append("'>Customer</th>")
                .append("<th style='").append(thStyle).append("'>Product Name</th>")
                .append("<th style='").append(thStyle).append("'>Product Price</th>")
                .append("<th style='").append(thStyle).append("'>Amount</th></tr>");


        for (Purchase purchase : purchases) {
            reportHtml.append("<tr>")
                    .append("<td style='").append(tdStyle).append("'>").append(purchase.getCustomer().getName()).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(purchase.getProduct().getName()).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(String.format(Locale.US, "%.2f",purchase.getProduct().getPrice())).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(String.format(Locale.US, "%.2f", purchase.getAmount())).append("</td>")
                    .append("</tr>");
        }



        reportHtml.append("</table><br>");

        // Refunds table
        reportHtml.append("<h2 style='text-align: center';>Refunds:</h2>")
                .append("<table style='").append(tableStyle).append("'><tr style='").append(thStyle).append("'>")
                .append("<th style='").append(thStyle).append("'>Customer</th>")
                .append("<th style='").append(thStyle).append("'>Product Name</th>")
                .append("<th style='").append(thStyle).append("'>Product Price</th>")
                .append("<th style='").append(thStyle).append("'>Purchased Amount</th>")
                .append("<th style='").append(thStyle).append("'>Refunded Amount</th></tr>");


        for (Object[] row : refundData) {
            reportHtml.append("<tr>")
                    .append("<td style='").append(tdStyle).append("'>").append(row[2]).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(row[3]).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(String.format(Locale.US, "%.2f", row[4])).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(String.format(Locale.US, "%.2f", row[0])).append("</td>")
                    .append("<td style='").append(tdStyle).append("'>").append(String.format(Locale.US, "%.2f", row[1])).append("</td>")
                    .append("</tr>");
        }

        reportHtml.append("</table>")
                .append("</body></html>");



        return reportHtml.toString();
    }
}
