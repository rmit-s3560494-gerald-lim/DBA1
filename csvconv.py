csvFile = "BUSINESS_NAMES_201803.csv"
csvNew = "bn.sql"

def replaceNull(column):
    if column == "":
        return "null, "
    else:
        return "'" + column + "', "

def replaceABNNull(column):
    column = column.replace("\n", "")
    if column == "" or None:
        return "null"
    else:
        return column

def changeDate(date):
    if date == "":
        return "null, "
    else:
        d, m, y = date.split("/")
        return "'" + y + "-" + m + "-" + d + "', "

def replaceApostrophe(column):
    column = column.replace("'", "''")
    return "'" + column + "', "

with open(csvFile, "r") as inputFile:
    next(inputFile)
    with open(csvNew, "w") as outputFile:
        for line in inputFile:
            newSql = ""
            delim = line
            REGISTER_NAME, BN_NAME, BN_STATUS, BN_REG_DT, BN_CANCEL_DT, BN_RENEW_DT, BN_STATE_NUM, BN_STATE_OF_REG, BN_ABN = delim.split('\t')
            newSql += "INSERT INTO BN " + "VALUES ('BUSINESS_NAMES', "
            newSql += replaceApostrophe(BN_NAME)
            newSql += replaceNull(BN_STATUS)
            newSql += changeDate(BN_REG_DT)
            newSql += changeDate(BN_CANCEL_DT)
            newSql += changeDate(BN_RENEW_DT)
            newSql += replaceNull(BN_STATE_NUM)
            newSql += replaceNull(BN_STATE_OF_REG)
            newSql += "'"
            newSql += replaceABNNull(BN_ABN)
            newSql += "'"
            newSql += ");\n"
            outputFile.write(newSql)
            
