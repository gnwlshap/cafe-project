package controller;

import controller.action.Action;
import controller.action.AddCartAction;
import controller.action.ClearCartAction;
import controller.action.CreateCommentAction;
import controller.action.CreateScrapAction;
import controller.action.DeleteCommentAction;
import controller.action.DeleteNoticeAction;
import controller.action.DeleteReviewAction;
import controller.action.DeleteScrapAction;
import controller.action.DuplIdCheckAction;
import controller.action.DuplPhoneCheckAction;
import controller.action.LeaveAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.ModifyNoticeAction;
import controller.action.ModifyReviewAction;
import controller.action.MyWriteListAction;
import controller.action.OrderAction;
import controller.action.OrderDetailListAction;
import controller.action.OrderListAction;
import controller.action.ReadAllCafeAction;
import controller.action.ReadAllMenuAction;
import controller.action.ReadCartAction;
import controller.action.ReadNoticeAction;
import controller.action.ReadReviewAction;
import controller.action.ReadScrapAction;
import controller.action.ReadTotalPriceAction;
import controller.action.RegistAction;
import controller.action.RemoveCartAction;
import controller.action.SearchNoticeAction;
import controller.action.SearchReviewAction;
import controller.action.UpdateCommentAction;
import controller.action.UpdateNoticeAction;
import controller.action.UpdateReviewAction;
import controller.action.WriteNoticeAction;
import controller.action.WriteReviewAction;
import controller.action.updateMypageAction;
import controller.action.SearchCafeAction;
import controller.action.SearchCommentAction;

public class ActionFactory {

	private ActionFactory() {
	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		if (command.equals("regist")) {
			action = new RegistAction();
		} else if (command.equals("searchnotice")) {
			action = new SearchNoticeAction();
		} else if (command.equals("searchcafe")) {
			action = new SearchCafeAction();
		} else if (command.equals("searchreview")) {
			action = new SearchReviewAction();
		} else if (command.equals("deletenotice")) {
			action = new DeleteNoticeAction();
		} else if (command.equals("login")) {
			action = new LoginAction();
		} else if (command.equals("writenotice")) {
			action = new WriteNoticeAction();
		} else if (command.equals("updatenotice")) {
			action = new UpdateNoticeAction();
		} else if (command.equals("addCart")) {
			action = new AddCartAction();
		} else if (command.equals("logout")) {
			action = new LogoutAction();
		} else if (command.equals("updatemypage")) {
			action = new updateMypageAction();
		} else if (command.equals("leave")) {
			action = new LeaveAction();
		} else if (command.equals("createcomment")) {
			action = new CreateCommentAction();
		} else if (command.equals("getcomment")) {
			action = new SearchCommentAction();
		} else if (command.equals("readCart")) {
			action = new ReadCartAction();
		} else if (command.equals("deletecomment")) {
			action = new DeleteCommentAction();
		} else if (command.equals("updatecomment")) {
			action = new UpdateCommentAction();
		} else if (command.equals("removeCart")) {
			action = new RemoveCartAction();
		} else if (command.equals("clearCart")) {
			action = new ClearCartAction();
		} else if (command.equals("order")) {
			action = new OrderAction();
		} else if (command.equals("duplidcheck")) {
			action = new DuplIdCheckAction();
		} else if (command.equals("duplphonecheck")) {
			action = new DuplPhoneCheckAction();
		} else if (command.equals("writereview")) {
			action = new WriteReviewAction();
		} else if (command.equals("readScrap")) {
			action = new ReadScrapAction();
		} else if (command.equals("createscrap")) {
			action = new CreateScrapAction();
		} else if (command.equals("deletescrap")) {
			action = new DeleteScrapAction();
		} else if (command.equals("readTotalPrice")) {
			action = new ReadTotalPriceAction();
		} else if (command.equals("updatereview")) {
			action = new UpdateReviewAction();
		} else if (command.equals("deletereview")) {
			action = new DeleteReviewAction();
		}  else if (command.equals("orderdetaillist")) {
			action = new OrderDetailListAction();
		}  else if (command.equals("orderlist")) {
			action = new OrderListAction();
		}  else if (command.equals("mywritelist")) {
			action = new MyWriteListAction();
		}  else if (command.equals("readnotice")) {
			action = new ReadNoticeAction();
		}  else if (command.equals("readallcafe")) {
			action = new ReadAllCafeAction();
		}  else if (command.equals("readreview")) {
			action = new ReadReviewAction();
		}  else if (command.equals("modifynotice")) {
			action = new ModifyNoticeAction();
		}  else if (command.equals("modifyreview")) {
			action = new ModifyReviewAction();
		}  else if(command.equals("readallmenu")) {
			action = new ReadAllMenuAction();
		}
 
		return action;
	}
}
