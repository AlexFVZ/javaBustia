#Alex Valerozo

from proven.shop.Model.ShopModel import shopModel
from proven.shop.Controller.ShopController import shopController

class ShopApp:
    def main(self):
        model=shopModel()
        control=shopController(model)

if __name__ == "__main__":
    # Start the application
    app = ShopApp()
    app.main()