#Alex Valerozo

from proven.shop.View.ShopMenu import ShopMenu

class ShopView:
    def __init__(self, control, model):
        """
        Initializes the view with a controller and a model.
        :param control: FileController, controller instance
        :param model: FileModel, model instance
        """
        self.control = control
        self.menu = ShopMenu()  # This creates the menu in the view

    def get_user_choice(self):
        try:
            return int(input("Select an option: "))
        except ValueError:
            return -1

    def display(self):
        """
        Shows the main menu and processes user actions.
        """
        while True:
            self.menu.show()
            action = self.menu.get_selected_option_action_command()
            self.process_action(action)

    def process_action(self, action):
        """
        Handles the actions sent from the menu.
        :param action: str, action command
        """
        if action:
            self.control.process_request(action)


    def show_cart(self, cart_content):
        print("\nShopping Cart:")
        print(cart_content)

    def show_message(self, message):
        print(message)

    def get_quantity(self):
        try:
            return int(input("Enter quantity: "))
        except ValueError:
            return 0

    def get_product_selection(self):
        try:
            return int(input("Enter product number: "))
        except ValueError:
            return -1

    def show_products(self, products):
        print("\nAvailable Products:")
        for idx, product in enumerate(products):
            print(f"[{idx}] {product}")

    def ask_to_clear_cart(self):
        return input("Do you want to clear the cart? (yes/no): ").lower() == "yes"