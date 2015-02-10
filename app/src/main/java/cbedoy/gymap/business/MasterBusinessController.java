package cbedoy.gymap.business;

import org.pademobile.com.currency.interfaces.ICurrencyTransactionDelegate;
import org.pademobile.com.currency.interfaces.ICurrencyTransactionHandler;
import org.pademobile.com.interfaces.IMessageRepresentationHandler;
import org.pademobile.com.interfaces.ITransactionInformationDelegate;
import org.pademobile.com.interfaces.ITransactionInformationHandler;
import org.pademobile.com.login.interfaces.ILoginTransactionDelegate;
import org.pademobile.com.login.interfaces.ILoginTransactionHandler;
import org.pademobile.com.objects.Memento;
import org.pademobile.com.otp.interfaces.IOTPTransactionDelegate;
import org.pademobile.com.otp.interfaces.IOTPTransactionHandler;
import org.pademobile.com.payment.interfaces.IPaymentOriginsTransactionDelegate;
import org.pademobile.com.payment.interfaces.IPaymentOriginsTransactionHandler;
import org.pademobile.com.serviceinformation.interfaces.IServiceInformationTransactionDelegate;
import org.pademobile.com.serviceinformation.interfaces.IServiceInformationTransactionHandler;
import org.pademobile.com.serviceselector.interfaces.IServiceSelectorTransactionDelegate;
import org.pademobile.com.serviceselector.interfaces.IServiceSelectorTransactionHandler;
import org.pademobile.com.signup.interfaces.ISignUpConfirmationTransactionDelegate;
import org.pademobile.com.signup.interfaces.ISignUpConfirmationTransactionHandler;
import org.pademobile.com.signup.interfaces.ISignUpNipTransactionDelegate;
import org.pademobile.com.signup.interfaces.ISignUpPinTransactionHandler;
import org.pademobile.com.signup.interfaces.ISignUpTransactionDelegate;
import org.pademobile.com.signup.interfaces.ISignUpTransactionHandler;
import org.pademobile.com.ticket.interfaces.ITicketTransactionDelegate;
import org.pademobile.com.ticket.interfaces.ITicketTransactionHandler;
import org.pademobile.com.timeout.interfaces.ITimeOutTransactionDelegate;
import org.pademobile.com.timeout.interfaces.ITimeOutTransactionHandler;
import org.pademobile.com.usercertification.interfaces.IUserCertificationTransactionDelegate;
import org.pademobile.com.usercertification.interfaces.IUserCertificationTransactionHandler;
import org.pademobile.com.userselector.interfaces.IUserSelectorTransactionDelegate;
import org.pademobile.com.userselector.interfaces.IUserSelectorTransactionHandler;
import org.pademobile.com.wallet.interfaces.IWalletTransactionDelegate;
import org.pademobile.com.wallet.interfaces.IWalletTransactionHandler;
import org.pademobile.com.walletselector.interfaces.IWalletSelectorTransactionDelegate;
import org.pademobile.com.walletselector.interfaces.IWalletSelectorTransactionHandler;

import java.util.ArrayList;
import java.util.HashMap;

import cbedoy.gymap.artifacts.BusinessController;
import cbedoy.gymap.artifacts.Memento;

import static org.pademobile.com.interfaces.IMessageRepresentationHandler.MESSAGE_REPRESENTATION_CODE.K_NEED_CERTIFICATION;

public class MasterBusinessController extends BusinessController implements ILoginTransactionHandler, IUserCertificationTransactionHandler, IUserSelectorTransactionHandler, IServiceInformationTransactionHandler, IServiceSelectorTransactionHandler, IPaymentOriginsTransactionHandler, IWalletSelectorTransactionHandler, ICurrencyTransactionHandler, IWalletTransactionHandler, ITimeOutTransactionHandler, ITransactionInformationDelegate, IOTPTransactionHandler, ITicketTransactionHandler,
        ISignUpTransactionHandler,ISignUpPinTransactionHandler,ISignUpConfirmationTransactionHandler {

    public static volatile int FORCE_CLOSE_INAPP = -1;
    private IOTPTransactionDelegate mOTPController;
    private ILoginTransactionDelegate mLoginController;
    private ISignUpTransactionDelegate mSignUpController;
    private ISignUpNipTransactionDelegate mSignUpNipController;
    private ISignUpConfirmationTransactionDelegate mSignUpConfirmationController;
    private ITicketTransactionDelegate mTicketController;
    private ITimeOutTransactionDelegate mTimeoutController;
    private ICurrencyTransactionDelegate mCurrenciesController;
    private IUserSelectorTransactionDelegate mUserSelectorController;
    private ArrayList<IWalletTransactionDelegate> mWalletControllers;
    private IPaymentOriginsTransactionDelegate mPaymentOriginsController;
    private IWalletSelectorTransactionDelegate mWalletSelectorController;
    private ITransactionInformationHandler mTransactionInformationHandler;
    private IServiceSelectorTransactionDelegate mServiceSelectorController;
    private IUserCertificationTransactionDelegate mUserCertificationController;
    private IServiceInformationTransactionDelegate mServiceInformationController;

    public MasterBusinessController() {
        mWalletControllers = new ArrayList<IWalletTransactionDelegate>();
    }

    public void setTicketController(ITicketTransactionDelegate ticketController) {
        mTicketController = ticketController;
    }

    public void setOtpController(IOTPTransactionDelegate otpController) {
        mOTPController = otpController;
    }

    public void setLoginController(ILoginTransactionDelegate loginController) {
        mLoginController = loginController;
    }

    public void setSignUpController(ISignUpTransactionDelegate mSignUpController) {
        this.mSignUpController = mSignUpController;
    }

    public void setSignUpNipController(ISignUpNipTransactionDelegate mSignUpNipController) {
        this.mSignUpNipController = mSignUpNipController;
    }


    public void setSignUpConfirmationController(ISignUpConfirmationTransactionDelegate mSignUpConfirmationController) {
        this.mSignUpConfirmationController = mSignUpConfirmationController;
    }

    public void addWalletController(IWalletTransactionDelegate walletController) {
        if (walletController != null) {
            mWalletControllers.add(walletController);
        }
    }

    public void setTimeoutController(ITimeOutTransactionDelegate timeoutController) {
        mTimeoutController = timeoutController;
    }

    public void setCurrenciesController(ICurrencyTransactionDelegate currenciesController) {
        mCurrenciesController = currenciesController;
    }

    public void setUserSelectorController(IUserSelectorTransactionDelegate userSelectorController) {
        mUserSelectorController = userSelectorController;
    }

    public void setWalletSelectorController(IWalletSelectorTransactionDelegate walletSelectorController) {
        mWalletSelectorController = walletSelectorController;
    }

    public void setPaymentOriginsController(IPaymentOriginsTransactionDelegate paymentOriginsController) {
        mPaymentOriginsController = paymentOriginsController;
    }

    public void setServiceSelectorController(IServiceSelectorTransactionDelegate serviceSelectorController) {
        mServiceSelectorController = serviceSelectorController;
    }

    public void setTransactionInformationHandler(ITransactionInformationHandler transactionInformationHandler) {
        mTransactionInformationHandler = transactionInformationHandler;
    }

    public void setUserCertificationController(IUserCertificationTransactionDelegate userCertificationController) {
        mUserCertificationController = userCertificationController;
    }

    public void setServiceInformationController(IServiceInformationTransactionDelegate serviceInformationController) {
        mServiceInformationController = serviceInformationController;
    }

    @Override
    public void timerFinished() {
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();

        boolean sessionCanExpire = data.containsKey("session_can_expire") ? (Boolean) data.get("session_can_expire") : false;
        if (sessionCanExpire) {
            mTimeoutController.cancelTimer();

            mementoHandler.popDataFor(mLoginController);
            startTransaction();
        }
    }

    public void startTransaction()
    {
        mLoginController.startLogin();
    }

    public void finishTransaction()
    {
        if(mLoginController != null)
            mementoHandler.popDataFor(mLoginController);
    }

    @Override
    public void userAuthenticated() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("session_can_expire", true);
        mementoHandler.setStateForOwner(data, this);

        mTimeoutController.startTimer();

        //mUserCertificationController.certifyUser(); // TODO

        mPaymentOriginsController.loadPaymentOrigins();
    }

    @Override
    public void SignUp() {
        mSignUpController.startSignUp();
    }

    @Override
    public void userCertified() {
        mUserSelectorController.selectUser();
    }

    @Override
    public void certificationCanceled() {
        mPaymentOriginsController.representPaymentOrigins();
    }

    @Override
    public void userSelected() {
        mServiceInformationController.requestServiceInformation();
    }

    @Override
    public void serviceInformationReceived() {
        mCurrenciesController.getAvailableCurrencies();
    }

    @Override
    public void currencySelected() {
        mServiceSelectorController.selectService();
    }

    @Override
    public void serviceSelected() {
        mPaymentOriginsController.loadPaymentOrigins();
    }

    @Override
    public void noValidPaymentOrigin() {
        mWalletSelectorController.selectWallet();
    }

    @Override
    public void paymentOriginSelected() {
        mTransactionInformationHandler.performTransaction();
    }

    @Override
    public void transactionResponse(HashMap<String, Object> response) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("transaction_response", response);
        mementoHandler.setStateForOwner(data, this);

        boolean status = (Boolean) response.get("status");
        if (status) {
            mOTPController.startOTPRequest();
        } else {
            String error = response.containsKey("error") ? response.get("error").toString() : "error";
            String message = response.containsKey("message") ? response.get("message").toString() : "error";


            if (response.containsKey("pendiente") && (Boolean) response.get("pendiente")) {
                mUserCertificationController.certifyUser();
            } else {
                if (error.compareToIgnoreCase("necesita_certificacion") == 0) {
                   messageRepresentationHandler.showCodeMessage(K_NEED_CERTIFICATION, message);
                } else {
                    if (error.compareToIgnoreCase("no_hay_saldo") == 0) {
                        messageRepresentationHandler.showCode(IMessageRepresentationHandler.MESSAGE_REPRESENTATION_CODE.K_NOT_ENOUGH_FOUNDS);
                    } else {
                        if (error.compareToIgnoreCase("importe_maximo") == 0) {
                            messageRepresentationHandler.showCodeMessage(IMessageRepresentationHandler.MESSAGE_REPRESENTATION_CODE.K_MAX_AMOUNT, message);
                        } else {
                            messageRepresentationHandler.showCodeTitleMessage(IMessageRepresentationHandler.MESSAGE_REPRESENTATION_CODE.K_GENERAL_ERROR, error, message);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void OTPCanceled() {
        mPaymentOriginsController.representPaymentOrigins();
    }

    @Override
    public void OTPConfirmed() {
        mTicketController.loadTicketInformation();
    }

    @Override
    public void finishedTicketRepresentation() {

    }

    @Override
    public void addNewOriginRequested() {
        mWalletSelectorController.selectWallet();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void newWalletSelected() {
        Memento memento = mementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> selectedWallet = (HashMap<String, Object>) data.get("selected_wallet");

        String clase = selectedWallet.get("clase").toString();
        for (IWalletTransactionDelegate walletController : mWalletControllers) {
            String controllerType = walletController.getWalletType();
            if (clase.compareToIgnoreCase(controllerType) == 0) {
                walletController.addWallet();
            }
        }
    }

    @Override
    public void walletAdded() {
        serviceSelected();
    }

    @Override
    public void userDontSupport() {
        mWalletSelectorController.dontSupportFromJumio();
    }

    @Override
    public void signUpReady() {
        mSignUpNipController.startSignUpNip();
    }

    @Override
    public void signUpNipReady() {
        mSignUpConfirmationController.startSignUpConfirmation();
    }

    @Override
    public void cancelSignUpNip() {
        mSignUpController.startSignUp();
    }

    @Override
    public void confirmationCorrect()
    {
        Memento topMemento = mementoHandler.getTopMemento();
        HashMap<String, Object> mementoData = topMemento.getMementoData();
        mementoData.put("user_registered", true);
        mementoHandler.setStateForOwner(mementoData, this);

        mLoginController.startLogin();
    }

    @Override
    public void cancelSignUpConfirmation() {
        mSignUpController.startSignUp();
    }

}
