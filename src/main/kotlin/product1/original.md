3.	Specific Requirements

The specific requirements are –

3.1	Functionality

Introduction –

This subsection contains the requirements for the e-store. These requirements are organized by the features discussed in the vision document. Features from vision documents are then refined into use case diagrams and to sequence diagram to best capture the functional requirements of the system. All these functional requirements can be traced using tractability matrix.

3.1.1	Sell Configured to Ordered Products.

3.1.1.1	The system shall display all the products that can be configured.
3.1.1.2	The system shall allow user to select the product to configure.
3.1.1.3	The system shall display all the available components of the product to configure
3.1.1.4	The system shall enable user to add one or more component to the configuration.
3.1.1.5	The system shall notify the user about any conflict in the current configuration.
3.1.1.6	The system shall allow user to update the configuration to resolve conflict in the current configuration.
3.1.1.7	The system shall allow user to confirm the completion of current configuration

3.1.2	Provide comprehensive product details.


3.1.2.1	The system shall display detailed information of the selected products.
3.1.2.2	The system shall provide browsing options to see product details.

3.1.3	Detailed product Categorizations

The system shall display detailed product categorization to the user.

3.1.4	Provide Search facility.

The system shall enable user to enter the search text on the screen.

The system shall enable user to select multiple options on the screen to search.

The system shall display all the matching products based on the search

The system shall display only 10 matching result on the current screen.

The system shall enable user to navigate between the search results.

The system shall notify the user when no matching product is found on the search.


3.1.5	Maintain customer profile.

The system shall allow user to create profile and set his credential.

The system shall authenticate user credentials to view the profile.

The system shall allow user to update the profile information.

3.1.6	Provide personalized profile
.
The system shall display both the active and completed order history in the customer profile.

The system shall allow user to select the order from the order history.

The system shall display the detailed information about the selected order.

The system shall display the most frequently searched items by the user in the profile.

The system shall allow user to register for newsletters and surveys in the profile.

3.1.7	Provide Customer Support.

The system shall provide online help, FAQ’s customer support, and sitemap options for customer support.

The system shall allow user to select the support type he wants.

The system shall allow user to enter the customer and product information for the support.

The system shall display the customer support contact numbers on the screen.

The system shall allow user to enter the contact number for support personnel to call.

The system shall display the online help upon request.

The system shall display the FAQ’s upon request.

3.1.8	Email confirmation.

The system shall maintain customer email information as a required part of customer profile.

The system shall send an order confirmation to the user through email.

3.1.9	Detailed invoice for customer.

The system shall display detailed invoice for current order once it is confirmed.

The system shall optionally allow user to print the invoice.

3.1.10	Provide shopping cart facility.

The system shall provide shopping cart during online purchase.

The system shall allow user to add/remove products in the shopping cart.

3.1.11	Provide multiple shipping methods.

The system shall display different shipping options provided by shipping department.

The system shall enable user to select the shipping method during payment process.

The system shall display the shipping charges.

The system shall display tentative duration for shipping.


3.1.12	Online tracking of shipments

The system shall allow user to enter the order information for tracking.

The system shall display the current tracking information about the order.

3.1.13	Provide online Tax Calculations

The system shall calculate tax for the order.

The system shall display tax information for the order.

3.1.14	Allow multiple payment methods.
.
The system shall display available payment methods for payment.

The system shall allow user to select the payment method for order.


3.1.15	Allow online change or cancellation of order.

The system shall display the orders that are eligible to change.

The system shall allow user to select the order to be changed.

The system shall allow user to cancel the order

The system shall allow user to change shipping, payment method.

The system shall notify the user about any changes made to the order.

3.1.16	Allow Online Product reviews and ratings

The system shall display the reviews and ratings of each product, when it is selected.

The system shall enable the user to enter their reviews and ratings.

3.1.17	Offer financing options.

The system shall display all the available financing options.

The system shall allow user to select the financing option.

The system shall notify the use about the financing request.

3.1.18	Provide detailed sitemap.

The system shall allow user to view detailed sitemap.

3.1.19	Offer online promotions and rewards.

The system shall display all the available promotions to the user.

The system shall allow user to select available promotion.

3.1.20	Online Purchase of products.

The system shall allow user to confirm the purchase.

The system shall enable user to enter the payment information.


3.2	Usability
3.2.1	Graphical User Interface

The system shall provide a uniform look and feel between all the web pages.

The system shall provide a digital image for each product in the product catalog.

The system shall provide use of icons and toolbars.

3.2.2	Accessibility

The system shall provide handicap access.

The system shall provide multi language support.


3.3	Reliability & Availability
3.3.1	Back-end Internal Computers

The system shall provide storage of all databases on redundant computers with automatic switchover.

The system shall provide for replication of databases to off-site storage locations.

The system shall provide RAID V Disk Stripping on all database storage disks.

3.3.2	Internet Service Provider

The system shall provide a contractual agreement with an internet service provider for T3 access with 99.9999% availability.

The system shall provide a contractual agreement with an internet service provider who can provide 99.999% availability through their network facilities onto the internet.

3.4	Performance

The product shall be based on web and has to be run from a web server.

The product shall take initial load time depending on internet connection strength which also depends on the media from which the product is run.

The performance shall depend upon hardware components of the client/customer.

3.5	Security
3.5.1	Data Transfer

The system shall use secure sockets in all transactions that include any confidential customer information.

The system shall automatically log out all customers after a period of inactivity.

The system shall confirm all transactions with the customer’s web browser.

The system shall not leave any cookies on the customer’s computer containing the user’s password.

The system shall not leave any cookies on the customer’s computer containing any of the user’s confidential information.

3.5.2	Data Storage

The customer’s web browser shall never display a customer’s password.  It shall always be echoed with special characters representing typed characters.

The customer’s web browser shall never display a customer’s credit card number after retrieving from the database.  It shall always be shown with just the last 4 digits of the credit card number.

The system’s back-end servers shall never display a customer’s password.  The customer’s password may be reset but never shown.

The system’s back-end servers shall only be accessible to authenticated administrators.

The system’s back-end databases shall be encrypted.


3.6	Supportability
3.6.1	Configuration Management Tool

The source code developed for this system shall be maintained in configuration management tool.

3.7	Design Constraints
3.7.1	Standard Development Tools

The system shall be built using a standard web page development tool that conforms to either IBM’s CUA standards or Microsoft’s GUI standards.

3.7.2	Web Based Product

          There are no memory requirements 
          The computers must be equipped with web browsers such as Internet explorer. 
         The product must be stored in such a way that allows the client easy access to it. 
         Response time for loading the product should take no longer than five minutes. 
         A general knowledge of basic computer skills is required to use the product 

3.8	On-line User Documentation and Help System Requirements
As the product is E-store, On-line help system becomes a critical component of the system which shall provide –
It shall provide specific guidelines to a user for using the E-Store system and within the system.
To implement online user help, link and search fields shall be provided.

3.9	Purchased Components
Not Applicable

3.10	Interfaces

There are many types of interfaces as such supported by the E-Store software system namely; User Interface, Software Interface and Hardware Interface.
The protocol used shall be HTTP.
The Port number used will be 80.
There shall be logical address of the system in IPv4 format.
3.10.1	User Interfaces
The user interface for the software shall be compatible to any browser such as Internet Explorer, Mozilla or Netscape Navigator by which user can access to the system.
The user interface shall be implemented using any tool or software package like Java Applet, MS Front Page, EJB etc.

3.10.2	Hardware Interfaces

Since the application must run over the internet, all the hardware shall require to connect internet will be hardware interface for the system. As for e.g. Modem, WAN – LAN, Ethernet Cross-Cable.

3.10.3	Software Interfaces

1.	The e-store system shall communicate with the Configurator to identify all the available components to configure the product.
2.	The e-store shall communicate with the content manager to get the product specifications, offerings and promotions.
3.	The e-store system shall communicate with billPay system to identify available payment methods , validate the payments and process payment.
4.	The e-store system shall communicate to credit management system for handling financing options.
5.	The e-store system shall communicate with CRM system to provide support.
6.	The e-store system shall communicate with Sales system for order management.
7.	The e-store system shall communicate with shipping system for tracking orders and updating of shipping methods.
8.	The e-store system shall communicate with external Tax system to calculate tax.
9.	The e-store system shall communicate with export regulation system to validate export regulations.
10. The system shall be verisign like software which shall allow the users to complete secured transaction. This usually shall be the third party software system which is widely used for internet transaction.

3.10.4	Communications Interfaces

The e-store system shall use the HTTP protocol for communication over the internet and for the intranet communication will be through TCP/IP protocol suite.

3.11	Licensing Requirements
Not Applicable
3.12	Legal, Copyright, and Other Notices

E-store should display the disclaimers, copyright, word mark, trademark and product warranties of the Marvel electronics and home entertainment.

3.13	Applicable Standards
It shall be as per the industry standard.
