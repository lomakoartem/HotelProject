package by.epam.hotel.command;

import by.epam.hotel.command.impl.ChangeAccessLevelCommand;
import by.epam.hotel.command.impl.ChangeStatusRoomCommand;
import by.epam.hotel.command.impl.CreateOrderCommand;
import by.epam.hotel.command.impl.DeleteOrderCommand;
import by.epam.hotel.command.impl.LanguageCommand;
import by.epam.hotel.command.impl.LogOutCommand;
import by.epam.hotel.command.impl.LoginCommand;
import by.epam.hotel.command.impl.NoCommand;
import by.epam.hotel.command.impl.OrderAdministrationCommand;
import by.epam.hotel.command.impl.PageCommand;
import by.epam.hotel.command.impl.PaymentCommand;
import by.epam.hotel.command.impl.RegistrationCommand;
import by.epam.hotel.command.impl.ChangeStatusOrderCommand;
import by.epam.hotel.command.impl.ShowAllRoomCommand;
import by.epam.hotel.command.impl.ShowClientBillCommand;
import by.epam.hotel.command.impl.ShowClientOrderCommand;
import by.epam.hotel.command.impl.ShowFreeRoomCommand;
import by.epam.hotel.command.impl.UserAdministrationCommand;

/**
 * The Class CommandFactory. A factory for creating Command objects.
 */
public class CommandFactory {

	/**
	 * Creates a new Command object.
	 * 
	 * @param action
	 *            the action from Servlet
	 * @return the command for some action
	 */
	public static ICommand createCommand(String action) {
		if (action != null)
			switch (action) {
			case "login":
				return new LoginCommand();
			case "logout":
				return new LogOutCommand();
			case "createOrder":
				return new CreateOrderCommand();
			case "showFreeRoom":
				return new ShowFreeRoomCommand();
			case "showMyOrder":
				return new ShowClientOrderCommand();
			case "language":
				return new LanguageCommand();
			case "registration":
				return new RegistrationCommand();
			case "delorder":
				return new DeleteOrderCommand();
			case "useradministration":
				return new UserAdministrationCommand();
			case "changeAccessLevel":
				return new ChangeAccessLevelCommand();
			case "orderadministration":
				return new OrderAdministrationCommand();
			case "setStatusOrder":
				return new ChangeStatusOrderCommand();
			case "showMyBill":
				return new ShowClientBillCommand();
			case "payment":
				return new PaymentCommand();
			case "roomadministration":
				return new ShowAllRoomCommand();
			case "setstatusroom":
				return new ChangeStatusRoomCommand();
			case "page":
				return new PageCommand();
			default:
				return new NoCommand();
			}
		return new NoCommand();
	}
}
