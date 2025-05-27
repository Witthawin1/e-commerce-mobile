async function fetchSaleItemByCondition(payload) {
  try {
    const request = new URLSearchParams(payload).toString();
    const response = await fetch(
      `http://localhost:8080/itb-mshop/v2/sale-items?${request}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        // body: JSON.stringify(payload),
      }
    );

    if (!response.ok) {
      throw new Error(`แก้ไขสินค้าไม่สำเร็จ (HTTP ${response.status})`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    return { error };
  }
}

export {fetchSaleItemByCondition}