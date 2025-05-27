async function fetchBrands() {
  try {
    const res = await fetch("http://localhost:8080/itb-mshop/v1/brands");
    const data = await res.json();
    return data;
  } catch (err) {
    throw err;
  }
}

async function fetchBrandById(id) {
  try {
    const res = await fetch(`http://localhost:8080/itb-mshop/v1/brands/${id}`);
    const data = await res.json();
    console.log(data);

    if (!res.ok) {
      throw new Error(`An error has occurred, the brand does not exist.`);
    }

    return data;
  } catch (err) {
    return { err };
  }
}

async function editBrand(id, payload) {
  try {
    const res = await fetch(`http://localhost:8080/itb-mshop/v1/brands/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });
    const data = await res.json();

    if (!res.ok) {
      throw new Error(`fail to edit brand`);
    }
    return data;
  } catch (err) {
    throw err;
  }
}

async function deleteBrandById(id) {
  try {
    const res = await fetch(`http://localhost:8080/itb-mshop/v1/brands/${id}`, {
      method: "DELETE",
    });

    if (!res.ok) {
      throw new Error(`fail to find brand by Id`);
    }
  } catch (err) {
    throw err;
  }
}

async function AddBrand(payload) {
  try {
    const res = await fetch("http://localhost:8080/itb-mshop/v1/brands", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });
    if (!res.ok) throw new Error("Failed to add brand");
    const data = await res.json();
    return data;
  } catch (err) {
    throw err;
  }
}
export { fetchBrands, fetchBrandById, editBrand, deleteBrandById, AddBrand };
