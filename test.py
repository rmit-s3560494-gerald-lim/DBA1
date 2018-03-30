import csv
import json

csvFile = open('BUSINESS_NAMES_201803.csv', 'r')
jsonNew = open('jsonNew.json', 'w')

def replaceNull(column):
    column = column.replace('\\', '\\\\')
    column = column.replace('\"', '\\\"')
    if column == "":
        return "null"
    else:
        return "\"" + column + "\""

def replaceABNNull(column):
    column = column.replace("\n", "")
    if column == "" or None:
        return "null"
    else:
        return "\"" + column + "\""

def changeDate(date):
    if date == "":
        return "null"
    else:
        return "\"" + date + "\""

with csvFile as inputFile:
    next(inputFile)
    with jsonNew as outputFile:
        for line in inputFile:
            newJson = "{"
            delim = line
            REGISTER_NAME, BN_NAME, BN_STATUS, BN_REG_DT, BN_CANCEL_DT, BN_RENEW_DT, BN_STATE_NUM, BN_STATE_OF_REG, BN_ABN = delim.split('\t')
            newJson += "\n"
            newJson += "\t\"BN_NAME\": "
            newJson += replaceNull(BN_NAME) + ",\n"
            newJson += "\t\"BN_STATUS\": "
            newJson += replaceNull(BN_STATUS) + ",\n"
            newJson += "\t\"BN_REG_DT\": "
            newJson += changeDate(BN_REG_DT) + ",\n"
            newJson += "\t\"BN_CANCEL_DT\": "
            newJson += changeDate(BN_CANCEL_DT) + ",\n"
            newJson += "\t\"BN_RENEW_DT\": "
            newJson += changeDate(BN_RENEW_DT) + ",\n"
            newJson += "\t\"BN_STATE_NUM\": "
            newJson += replaceNull(BN_STATE_NUM) + ",\n"
            newJson += "\t\"BN_STATE_OF_REG\": "
            newJson += replaceNull(BN_STATE_OF_REG) + ",\n"
            newJson += "\t\"BN_ABN\": "
            newJson += replaceABNNull(BN_ABN)
            newJson += "\n"
            newJson += "},\n"
            outputFile.write(newJson)