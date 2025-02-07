#Alex Valerozo

class ShoppingCart:
    def __init__(self):
        self.items = {}

    def add_product(self, product, quantity):
        if product in self.items:
            self.items[product] += quantity
        else:
            self.items[product] = quantity
        return f"{quantity} x {product.name} added to the cart."

    def show_cart(self):
        if not self.items:
            return "Your cart is empty."
        result = "\n".join([f"{product.name} x {qty} - ${product.price * qty:.2f}"
                            for product, qty in self.items.items()])
        total = sum(product.price * qty for product, qty in self.items.items())
        return f"{result}\n\nTotal: ${total:.2f}"

    def clear_cart(self):
        self.items.clear()