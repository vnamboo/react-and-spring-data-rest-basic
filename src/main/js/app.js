'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');
// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {payments: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/payments'}).done(response => {
			this.setState({payments: response.entity._embedded.payments});
		});
	}

	render() {
		return (
		 <div>
		    <NameForm/>
		 </div>
		)
	}
}
// end::app[]

// tag::payment-list[]
class PaymentList extends React.Component{
	render() {
		var payments = this.props.payments.map(payment =>
			<Payment key={payment.id} payment={payment}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Message</th>
						<th>Tag</th>
					</tr>
					{payments}
				</tbody>
			</table>
		)
	}
}
// end::payment-list[]

// tag::payment[]
class Payment extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.payment.message}</td>
				<td>{this.props.payment.tag}</td>
			</tr>
		)
	}
}
// end::payment[]

// tag::search
class NameForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    value: '',
    payments: []
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    client({method: 'GET', path: '/api/search/' + this.state.value}).done(response => {
                alert('Search result: ' + response.entity[0].tag);
    			this.setState({payments: response.entity});
    		});
    event.preventDefault();
  }

  render() {
    return (
    <div>
      <form onSubmit={this.handleSubmit}>
        <label>
          Name:
          <input type="text" value={this.state.value} onChange={this.handleChange} />
        </label>
        <input type="submit" value="Submit" />
      </form>
      <PaymentList payments={this.state.payments}/>
      </div>
    );
  }
}
// end::search

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]

