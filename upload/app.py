import subprocess

import flask
from flask import Flask, request, render_template, send_file

app = Flask(__name__)


@app.route("/")
def main():
    return render_template('Natbag2020.html')

@app.route("/image")
def image():
    return send_file('flight_image.jpg')

@app.route("/iframe")
def iframe():
    return ""

@app.route("/search")
def search():
    return subprocess.check_output(["java", "-classpath",
                                    "/home/ben/eclipse-workspace/Natbag2020/bin", "NatbagMain",
                                    request.args.get('outformat'), request.args.get('flighttype'),
                                    request.args.get('airline'), request.args.get('country'),
                                    request.args.get('city'), request.args.get('airport'),
                                    request.args.get('startdate'), request.args.get('enddate'),
                                    request.args.get('sunday'), request.args.get('monday'),
                                    request.args.get('tuesday'), request.args.get('wednesday'),
                                    request.args.get('thursday'), request.args.get('friday'),
                                    request.args.get('saturday')])