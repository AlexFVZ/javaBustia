#Alex Valerozo

from proven.shop.View.ShopView import ShopView

class shopController:
    def __init__(self, model):
        self.model = model
        self.view = ShopView(self, model)
        self.view.display()

    def process_request(self, action):
        """
        Processes the user action and delegates to the corresponding method.
        If the action is invalid, it shows an error message.
        """
        action = action if action else "wrong_action"
        if action == "buy_product":
            self.buy_product()
        elif action == "show_cart":
            self.show_cart()
        elif action == "generate_invoice":
            self.generate_invoice()
        elif action == "exit":
            self.exit_application()
        else:
            self.view.show_message("Wrong option. Please select a valid action.")

    def buy_product(self):
        products = self.model.get_products()
        self.view.show_products(products)
        product_index = self.view.get_product_selection()
        quantity = self.view.get_quantity()
        result = self.model.add_to_cart(product_index, quantity)
        self.view.show_message(result)

    def show_cart(self):
        cart_content = self.model.show_cart()
        self.view.show_cart(cart_content)

    def generate_invoice(self):
        cart_content = self.model.show_cart()
        self.view.show_cart(cart_content)
        if self.view.ask_to_clear_cart():
            self.model.clear_cart()
            self.view.show_message("Cart has been cleared.")
        else:
            self.view.show_message("You can continue shopping.")

    def exit_application(self):
        """Exit the application."""
        import sys
        sys.exit(0)