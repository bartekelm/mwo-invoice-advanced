package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
	private List<BigDecimal> net = new ArrayList<BigDecimal>();
	private List<BigDecimal> tax = new ArrayList<BigDecimal>();

	public void addProduct(Product product) {
		this.net.add(product.getPrice());
		this.tax.add(product.getPrice().multiply(product.getTaxPercent()));
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity < 1){
			throw new IllegalArgumentException("Quantity must be a positive integer.");
		}
		this.net.add(product.getPrice()
				.multiply(new BigDecimal(quantity)));
		this.tax.add(product.getPrice()
				.multiply(product.getTaxPercent())
				.multiply(new BigDecimal(quantity)));
	}

	public BigDecimal getSubtotal() {
		BigDecimal subTotal = new BigDecimal(0);
		
		for (BigDecimal i : net){
			subTotal = subTotal.add(i);
		}
		return subTotal;
	}

	public BigDecimal getTax() {
		BigDecimal subTax = new BigDecimal(0);
		
		for (BigDecimal i : tax){
			subTax = subTax.add(i);
		}
		return subTax;
	}

	public BigDecimal getTotal() {
		return this.getSubtotal().add(this.getTax());
	}
}
