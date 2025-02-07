#Alex Valerozo

from proven.shop.View.Menu import Menu
from proven.shop.View.Option import Option


class ShopMenu(Menu):
    """
    Displays the menu options for managing shop.
    """
    def __init__(self):
        super().__init__("hop Manager main menu")

        # add options to menu
        self.add_option(Option("Buy a product", "buy_product"))
        self.add_option(Option("Show shopping cart", "show_cart"))
        self.add_option(Option("Generate invoice", "generate_invoice"))
        self.add_option(Option("Exit", "exit"))