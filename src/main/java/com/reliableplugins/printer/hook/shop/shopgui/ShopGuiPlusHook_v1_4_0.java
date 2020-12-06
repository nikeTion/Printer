/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printer.hook.shop.shopgui;

import com.reliableplugins.printer.hook.shop.ShopHook;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.inventory.ItemStack;

public class ShopGuiPlusHook_v1_4_0 implements ShopHook
{
    @Override
    public double getPrice(ItemStack item)
    {
        return ShopGuiPlusApi.getItemStackPriceBuy(item);
    }
}