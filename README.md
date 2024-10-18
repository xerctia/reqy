# Reqy: Your Minimal HTTP Requester

Ever wish sending HTTP requests was as easy as ordering food online? Well, you’re in luck! Introducing <b>reqy</b>, the bare-bones HTTP client that sends requests and (actually) gets responses, unlike... well, we’ll leave that up to your imagination.

<hr />

### What is reqy?

Reqy is your new BFF for sending GET and POST requests from the command line. Whether you're a developer testing endpoints, or just someone who loves sending data into the void, reqy has you covered. Light, fast, and straight to the point.

##### Features:

- <b>GET & POST Support</b>: Send HTTP GET and POST requests like a pro.
- <b>Verbose mode</b>: Want to receive all the details? Just add a -V.
- <b>Form Data Handling</b>: Send form data easily for POST requests.
- <b>Works on Linux and Windows</b>: And if you try hard enough, it can even run on Mac.
- <b>No unwanted surprises</b>: Just plain ol' HTTP requests, no shady business. ...Really

<hr />

### Installation

#### Linux

`curl -sL https://raw.githubusercontent.com/xerctia/reqy/refs/heads/main/Linux/install-reqy.sh
 | bash
`

Or:

1. Clone the repo and go to the Linux folder.
2. Run the `install-reqy` script.
3. Start playing around with <b>reqy</b> now.

#### Windows

1. Clone the repo and go to the Windows folder.
2. Run the `install-reqy.bat` file as administrator.
3. Done. Now, go forth and have fun, my child.

<b>Note:</b> You need to have Java and JDK installed in order to run this command.

<hr />

### Usage

`reqy -U <url> -M <method> -F key1=value1 -F key2=value2
`

<b>Note:</b> When sending the URL, if it is the root or home path, i.e. if the URL is like http://example.com, entering this will not work as the command doesn't get any path. So, http:example.com/ should be passed instead of http://example.com (i.e. with an extra /).

#### Example

`reqy -U http://example.com -M GET`

`reqy -U http://localhost:3000/tryit -M POST -F username=test -F password=1234
`

<hr />

### Options

- <b>-U</b>: URL to hit
- <b>-M</b>: HTTP method (GET/POST)
- <b>-F</b>: Form data
- <b>-V</b>: Verbose mode, shows headers
- <b>-H</b>: List all options

<hr />

### Why reqy?

Because sometimes, you just want a lightweight HTTP requester that won’t question your life choices. And if you’re tired of manually crafting HTTP requests but still want to feel like a low-level hacker, reqy’s got your back.

Plus, it’s not like your other tools were going to send that request for you, right? 🤷

<hr />

### License

MIT License.
But no one really reads this part, do they?
