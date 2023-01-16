
package webservice;

import java.time.LocalDate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddReservation_QNAME = new QName("http://service/", "addReservation");
    private final static QName _AddReservationResponse_QNAME = new QName("http://service/", "addReservationResponse");
    private final static QName _GetHotel_QNAME = new QName("http://service/", "getHotel");
    private final static QName _GetHotelResponse_QNAME = new QName("http://service/", "getHotelResponse");
    private final static QName _RoomsToString_QNAME = new QName("http://service/", "roomsToString");
    private final static QName _RoomsToStringResponse_QNAME = new QName("http://service/", "roomsToStringResponse");
    private final static QName _SearchRoom_QNAME = new QName("http://service/", "searchRoom");
    private final static QName _SearchRoomResponse_QNAME = new QName("http://service/", "searchRoomResponse");
    private final static QName _ToString_QNAME = new QName("http://service/", "toString");
    private final static QName _ToStringResponse_QNAME = new QName("http://service/", "toStringResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddReservation }
     * 
     */
    public AddReservation createAddReservation() {
        return new AddReservation();
    }

    /**
     * Create an instance of {@link AddReservationResponse }
     * 
     */
    public AddReservationResponse createAddReservationResponse() {
        return new AddReservationResponse();
    }

    /**
     * Create an instance of {@link GetHotel }
     * 
     */
    public GetHotel createGetHotel() {
        return new GetHotel();
    }

    /**
     * Create an instance of {@link GetHotelResponse }
     * 
     */
    public GetHotelResponse createGetHotelResponse() {
        return new GetHotelResponse();
    }

    /**
     * Create an instance of {@link RoomsToString }
     * 
     */
    public RoomsToString createRoomsToString() {
        return new RoomsToString();
    }

    /**
     * Create an instance of {@link RoomsToStringResponse }
     * 
     */
    public RoomsToStringResponse createRoomsToStringResponse() {
        return new RoomsToStringResponse();
    }

    /**
     * Create an instance of {@link SearchRoom }
     * 
     */
    public SearchRoom createSearchRoom() {
        return new SearchRoom();
    }

    /**
     * Create an instance of {@link SearchRoomResponse }
     * 
     */
    public SearchRoomResponse createSearchRoomResponse() {
        return new SearchRoomResponse();
    }

    /**
     * Create an instance of {@link ToString }
     * 
     */
    public ToString createToString() {
        return new ToString();
    }

    /**
     * Create an instance of {@link ToStringResponse }
     * 
     */
    public ToStringResponse createToStringResponse() {
        return new ToStringResponse();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    /*public Reservation createReservation() {
        return new Reservation();
    }*/

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     */
    /*public LocalDate createLocalDate() {
        return new LocalDate();
    }*/

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link Position }
     * 
     */
    public Position createPosition() {
        return new Position();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddReservation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddReservation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "addReservation")
    public JAXBElement<AddReservation> createAddReservation(AddReservation value) {
        return new JAXBElement<AddReservation>(_AddReservation_QNAME, AddReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddReservationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddReservationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "addReservationResponse")
    public JAXBElement<AddReservationResponse> createAddReservationResponse(AddReservationResponse value) {
        return new JAXBElement<AddReservationResponse>(_AddReservationResponse_QNAME, AddReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotel }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetHotel }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "getHotel")
    public JAXBElement<GetHotel> createGetHotel(GetHotel value) {
        return new JAXBElement<GetHotel>(_GetHotel_QNAME, GetHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHotelResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetHotelResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "getHotelResponse")
    public JAXBElement<GetHotelResponse> createGetHotelResponse(GetHotelResponse value) {
        return new JAXBElement<GetHotelResponse>(_GetHotelResponse_QNAME, GetHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoomsToString }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RoomsToString }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "roomsToString")
    public JAXBElement<RoomsToString> createRoomsToString(RoomsToString value) {
        return new JAXBElement<RoomsToString>(_RoomsToString_QNAME, RoomsToString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoomsToStringResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RoomsToStringResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "roomsToStringResponse")
    public JAXBElement<RoomsToStringResponse> createRoomsToStringResponse(RoomsToStringResponse value) {
        return new JAXBElement<RoomsToStringResponse>(_RoomsToStringResponse_QNAME, RoomsToStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRoom }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchRoom }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "searchRoom")
    public JAXBElement<SearchRoom> createSearchRoom(SearchRoom value) {
        return new JAXBElement<SearchRoom>(_SearchRoom_QNAME, SearchRoom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRoomResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchRoomResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "searchRoomResponse")
    public JAXBElement<SearchRoomResponse> createSearchRoomResponse(SearchRoomResponse value) {
        return new JAXBElement<SearchRoomResponse>(_SearchRoomResponse_QNAME, SearchRoomResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ToString }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ToString }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "toString")
    public JAXBElement<ToString> createToString(ToString value) {
        return new JAXBElement<ToString>(_ToString_QNAME, ToString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ToStringResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ToStringResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service/", name = "toStringResponse")
    public JAXBElement<ToStringResponse> createToStringResponse(ToStringResponse value) {
        return new JAXBElement<ToStringResponse>(_ToStringResponse_QNAME, ToStringResponse.class, null, value);
    }

}
