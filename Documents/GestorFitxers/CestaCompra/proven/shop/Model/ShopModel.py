#Alex Valerozo

from proven.shop.Model.Product import Product
from proven.shop.Model.ShopCart import ShoppingCart

class shopModel:
    def __init__(self):
        self.products = [
            Product("Apple", 1.20),
            Product("Banana", 0.80),
            Product("Milk", 1.50)
        ]
        self.cart = ShoppingCart()

    def get_products(self):
        return self.products

    def add_to_cart(self, product_index, quantity):
        if 0 <= product_index < len(self.products):
            product = self.products[product_index]
            return self.cart.add_product(product, quantity)
        return "Invalid product selection."

    def show_cart(self):
        return self.cart.show_cart()

    def clear_cart(self):
        self.cart.clear_cart()