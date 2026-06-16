# python-ml/data_generator.py
import pandas as pd
import numpy as np
import json
from datetime import datetime, timedelta
import uuid

def generate_user_profile(user_id: str, inject_anomaly: str = None) -> list[dict]:
    transactions = []
    base_date = datetime(2024, 7, 1)
    balance = 85000.0

    for day_offset in range(180):
        date = base_date + timedelta(days=day_offset)
        day = date.day

        if day == 1:  # Salary
            amt = np.random.normal(75000, 2000)
            if inject_anomaly == "job_loss" and day_offset > 120:
                amt = np.random.normal(12000, 1000)  # 84% drop
            balance += amt
            transactions.append(_txn(user_id, date, amt, "CREDIT", "SALARY CREDIT EMPLOYER", balance))

        if day in [5, 20]:  # EMIs
            emi_amt = 18500
            if inject_anomaly == "credit_stacking" and 90 < day_offset < 106:
                emi_amt = 18500 + np.random.choice([12000, 15000, 9500])
            balance -= emi_amt
            transactions.append(_txn(user_id, date, emi_amt, "DEBIT", "EMI PAYMENT HDFC LOAN", balance))

        if day == 10:  # Utility
            amt = np.random.normal(3200, 300)
            balance -= amt
            transactions.append(_txn(user_id, date, amt, "DEBIT", "BESCOM ELECTRICITY BILL", balance))

        if inject_anomaly == "amb_wipeout" and day_offset == 150:
            wipeout = balance * 0.91
            balance -= wipeout
            transactions.append(_txn(user_id, date, wipeout, "DEBIT", "IMPS TRANSFER TO UNKNOWN ACC 9XXXXXXX", balance))

    return transactions

def _txn(user_id, date, amount, txn_type, narration, balance):
    return {
        "transaction_id": str(uuid.uuid4()),
        "user_id": user_id,
        "account_id": f"ACC{user_id[-4:]}",
        "timestamp": date.isoformat(),
        "amount": round(abs(amount), 2),
        "transaction_type": txn_type,
        "narration": narration,
        "balance_after": round(balance, 2),
        "bank_name": "HDFC"
    }

if __name__ == "__main__":
    all_txns = []

    # 20 normal users
    for i in range(20):
        all_txns.extend(generate_user_profile(f"USER_{i:04d}"))

    # anomalies
    all_txns.extend(generate_user_profile("USER_ANOM_JL", "job_loss"))
    all_txns.extend(generate_user_profile("USER_ANOM_CS", "credit_stacking"))
    all_txns.extend(generate_user_profile("USER_ANOM_AW", "amb_wipeout"))

    df = pd.DataFrame(all_txns)
    df.to_csv("../data/transactions_raw.csv", index=False)
    print(f"Generated {len(df)} transactions for {df['user_id'].nunique()} users")
